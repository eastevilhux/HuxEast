package com.good.job.models.main.home

import android.app.Application
import com.good.job.commons.EastViewModel

class HomeViewModel : EastViewModel<MainData>(application = Application()){

    override fun initVMData(): MainData {
        return MainData();
    }

}