package com.good.job.models.main

import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.good.framework.commons.BaseActivity
import com.good.job.R
import com.good.job.commons.Constants
import com.good.job.databinding.ActivityMainBinding
import com.good.job.viewmodels.main.MainViewModel

@Route(path = Constants.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun getVMClass(): Class<MainViewModel> {
        return MainViewModel::class.java;
    }

    override fun initView() {
    }

}