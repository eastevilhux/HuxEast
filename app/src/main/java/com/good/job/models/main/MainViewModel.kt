package com.good.job.models.main

import android.app.Application
import com.good.job.commons.EastViewModel

class MainViewModel(application: Application) : EastViewModel<MainData>(application) {

    override fun initVMData(): MainData =
        MainData()

}