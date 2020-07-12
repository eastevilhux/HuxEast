package com.good.job.models.main.splash

import android.app.Application
import android.os.Handler
import android.util.Log
import com.good.framework.entity.VMData
import com.good.framework.http.RetrofitConfigure
import com.good.framework.http.commons.BaseModel
import com.good.job.commons.EastViewModel
import kotlin.jvm.internal.ReflectionFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : EastViewModel<SplashData>(application){
    private val baseModel = BaseModel.instance

    override fun initVMData(): SplashData {
        var splashData = SplashData();
        splashData.code = VMData.Code.CODE_DEFAULT;
        return SplashData();
    }

    override fun initModel() {
        Thread(Runnable {
            appSplashData()
        }).start();
    }


    private fun appSplashData() = GlobalScope.launch{
        var result = baseModel.appSplashEvent();
        if(result.isSuccess){
            Log.d("what==>splash=>",result.data?.appkey);
        }
    }
}