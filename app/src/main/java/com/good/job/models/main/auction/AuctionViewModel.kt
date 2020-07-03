package com.good.job.models.main.auction

import android.app.Application
import com.good.job.commons.EastViewModel

class AuctionViewModel(application: Application) : EastViewModel<AuctionData>(application){

    override fun initVMData(): AuctionData {
        return AuctionData();
    }

}