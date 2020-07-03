package com.good.job.models.main.auction

import com.good.framework.commons.BaseFragment
import com.good.job.R
import com.good.job.databinding.FragmentAuctionBinding

class AuctionFragment : BaseFragment<FragmentAuctionBinding,AuctionViewModel>(){
    override fun getLayoutRes(): Int = R.layout.fragment_auction;

    override fun getVMClass(): Class<AuctionViewModel> {
        return AuctionViewModel::class.java;
    }
}