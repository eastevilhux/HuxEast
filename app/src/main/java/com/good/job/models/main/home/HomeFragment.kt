package com.good.job.models.main.home

import com.good.framework.commons.BaseFragment
import com.good.job.R
import com.good.job.databinding.FragmentHomeBinding
import com.good.job.databinding.FragmentHomeBindingImpl

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(){

    override fun getLayoutRes(): Int = R.layout.fragment_home;

    override fun getVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java;
    }

}