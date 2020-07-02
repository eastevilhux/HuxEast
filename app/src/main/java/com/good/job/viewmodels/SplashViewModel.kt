package com.good.job.viewmodels

import android.app.Application
import android.os.Handler
import com.good.framework.commons.BaseViewModel
import com.good.framework.entity.VMData
import com.good.job.commons.EastViewModel
import com.good.job.entity.SplashData

class SplashViewModel(application: Application) : EastViewModel<SplashData>(application){

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
}