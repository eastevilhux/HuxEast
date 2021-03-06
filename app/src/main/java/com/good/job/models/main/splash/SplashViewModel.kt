package com.good.job.models.main.splash

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.god.uikit.utils.isMobile
import com.good.framework.commons.mainThread
import com.good.framework.entity.VMData
import com.good.framework.http.commons.BaseModel
import com.good.framework.http.entity.Event
import com.good.framework.utils.SharedPreferencesUtil
import com.good.job.R
import com.good.job.commons.Constants
import com.good.job.commons.EastViewModel
import com.good.job.commons.SPKey
import com.good.job.commons.arouteJump
import com.good.job.entity.Param
import com.good.job.manager.AccountManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : EastViewModel<SplashData>(application) {
    companion object {
        const val TAG = "SplashViewModel=>";
    }

    private val baseModel = BaseModel.instance

    val event = MutableLiveData<Event>();

    override fun initVMData(): SplashData {
        var splashData = SplashData();
        splashData.code = VMData.Code.CODE_DEFAULT;
        return SplashData();
    }

    override fun initModel() {
        var appkey = SharedPreferencesUtil.getInstance(getApplication()).getStringData(SPKey.KEY_APPKEY) ?: "";
        Log.d(TAG,appkey);
        com.good.framework.http.commons.Constants.appKey = appkey;
        appSplashEvent();
        appBeforehand();
    }


    private fun appBeforehand() = GlobalScope.launch {
        var result = baseModel.appBeforehand();
        Log.d(TAG,result.msg);
        if (result.isSuccess) {
            com.good.framework.http.commons.Constants.serviceKey = result.data?.serviceKey;
            com.good.framework.http.commons.Constants.appKey = result.data?.appkey;
            Log.d(TAG,result.state.toString());
            if (result.state) {
                //该用户现处于登录缓存中,设置AES加密类型
                com.good.framework.http.commons.Constants.setAESType();
                com.good.framework.http.commons.Constants.userid = result.extended;
                cacheUserInfo()
            } else {
                //该用户处于未登录，设置RSA加密类型
                com.good.framework.http.commons.Constants.setRSAType();
                mainThread {
                    var sp = SplashData();
                    sp.requestCode = SplashData.CODE_JUMP_MAIN
                    data.value = sp;
                }
            }
        }else{
            error(code = result.code,msg = result.msg?:getString(R.string.error_system));
        }
    }


    private fun appSplashEvent() = GlobalScope.launch {
        var result = baseModel.appSplashEvent();
        if (result.isSuccess) {
            mainThread {
                event.value = result.data;
            }
        } else {
            mainThread {
                error(result.code, result.msg ?: "");
            }
        }
    }

    private fun     cacheUserInfo() = GlobalScope.launch{
        var result = baseModel.cacheUserInfo();
        if(result.isSuccess){
            AccountManager.instance.user = result.data;
            mainThread {
                var sp = SplashData();
                sp.requestCode = SplashData.CODE_JUMP_MAIN
                data.value = sp;
            }
        }else{
            //获取缓存信息失败, 需要提示重新调用接口，这个地方如果回调预请求接口可能会引起死循环

        }
    }
}