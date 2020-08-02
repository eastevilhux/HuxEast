package com.good.job.models.account.viewmodel

import android.app.Application
import android.util.Log
import com.good.framework.commons.mainThread
import com.good.framework.entity.VMData
import com.good.framework.http.commons.BaseModel
import com.good.framework.http.entity.Error
import com.good.framework.utils.SharedPreferencesUtil
import com.good.job.R
import com.good.job.commons.Constants
import com.good.job.commons.EastViewModel
import com.good.job.commons.SPKey
import com.good.job.manager.AccountManager
import com.good.job.models.account.AccountData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : EastViewModel<AccountData>(application) {
    companion object{
        const val TAG = "LoginViewModel";
    }

    private var baseModel = BaseModel.instance;

    override fun initVMData(): AccountData = AccountData();


    fun login(account : String,password : String,isLoading:Boolean = false) = GlobalScope.launch{
        if(isLoading){
            showLoading();
        }
        var result = baseModel.userLogin(account,password);
        if(result.isSuccess){
            //登录成功,缓存账号信息
            AccountManager.instance.user = result.data;
            com.good.framework.http.commons.Constants.serviceKey = result.data?.aeskey;
            com.good.framework.http.commons.Constants.appKey = result.tag;
            com.good.framework.http.commons.Constants.setAESType();
            data.value?.code = VMData.Code.CODE_SUCCESS;
            SharedPreferencesUtil.getInstance(getApplication()).saveData(SPKey.KEY_APPKEY,result.tag);
            mainThread {
                data.value?.let { postValue(it) };
            }
        }else{
            error(code=result.code,msg=result.msg?:getString(R.string.error_system));
        }
        if(isLoading)
            dismissLoading();
    }
}