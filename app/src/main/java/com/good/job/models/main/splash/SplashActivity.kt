package com.good.job.models.main.splash

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.good.framework.commons.BaseActivity
import com.good.framework.entity.VMData
import com.good.job.R
import com.good.job.commons.Constants
import com.good.job.databinding.ActivitySplashBinding

@Route(path = "/models/splash")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash;
    }

    override fun getVMClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java;
    }

    override fun initView() {
        dataBinding.splashac = this;

        viewModel.getLiveData().observe(this, Observer {
            when(it.code){
                VMData.Code.CODE_SUCCESS->{
                    ARouter.getInstance().build(Constants.MAIN).navigation();
                }
            }
        })
    }
}