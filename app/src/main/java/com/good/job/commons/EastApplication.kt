package com.good.job.commons

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.good.framework.utils.AppUtil

class EastApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        if(AppUtil.isDebug(applicationInfo)){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}