package com.good.job.models.account.viewmodel

import android.app.Application
import android.util.Log
import com.good.framework.commons.mainThread
import com.good.framework.http.commons.BaseModel
import com.good.framework.http.entity.Error
import com.good.job.commons.EastViewModel
import com.good.job.models.account.AccountData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : EastViewModel<AccountData>(application) {
    companion object{
        const val TAG = "LoginViewModel";
    }

    private var baseModel = BaseModel.instance;

    override fun initVMData(): AccountData = AccountData();


    fun login(account : String,password : String) = GlobalScope.launch{
        var result = baseModel.userLogin(account,password);
        Log.d(TAG,"login${result.code}");
        if(result.isSuccess){

        }else{
            mainThread {
                error.value = Error(result.code,result.msg?:"");
            }
        }
    }
}