package com.good.job.viewmodels.main

import android.app.Application
import com.good.job.commons.EastViewModel
import com.good.job.entity.MainData

class MainViewModel(application: Application) : EastViewModel<MainData>(application) {

    override fun initVMData(): MainData = MainData()

}