package com.good.job.models.main.commodity

import android.app.Application
import com.good.job.commons.EastViewModel

class CommlistViewModel(application: Application) : EastViewModel<CommData>(application) {

    override fun initVMData(): CommData {
        return CommData();
    }

}