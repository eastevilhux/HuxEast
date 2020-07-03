package com.good.job.models.main.commodity

import com.good.framework.commons.BaseFragment
import com.good.job.R
import com.good.job.databinding.FragmentCommlistBinding;

class CommlistFragment : BaseFragment<FragmentCommlistBinding,CommlistViewModel>(){
    override fun getLayoutRes(): Int = R.layout.fragment_commlist;

    override fun getVMClass(): Class<CommlistViewModel> {
        return CommlistViewModel::class.java;
    }

}