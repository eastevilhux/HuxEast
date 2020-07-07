package com.good.job.models.main.splash

import android.app.Application
import android.os.Handler
import com.good.framework.entity.VMData
import com.good.framework.http.RetrofitConfigure
import com.good.job.commons.EastViewModel
import kotlin.jvm.internal.ReflectionFactory

class SplashViewModel(application: Application) : EastViewModel<SplashData>(application){
    private val baseService = RetrofitConfigure.instance.baseService;

    override fun initVMData(): SplashData {
        var splashData = SplashData();
        splashData.code = VMData.Code.CODE_DEFAULT;
        return SplashData();
    }

    override fun initModel() {
        super.initModel()

        Handler().postDelayed(Runnable {
            getData()!!.code = VMData.Code.CODE_SUCCESS;
            postValue(getData()!!);
        },3000);
    }


    private fun appSplashData(){
        baseService.appBeforehand()
    }
}