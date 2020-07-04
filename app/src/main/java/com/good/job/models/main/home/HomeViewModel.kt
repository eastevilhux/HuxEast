package com.good.job.models.main.home

import android.app.Application
import com.good.job.commons.EastViewModel

class HomeViewModel(application: Application) : EastViewModel<MainData>(application){

    override fun initVMData(): MainData {
        return MainData();
    }

}