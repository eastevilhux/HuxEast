package com.good.job.models.main.splash

import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.good.framework.commons.BaseActivity
import com.good.framework.entity.VMData
import com.good.job.R
import com.good.job.commons.AppActivity
import com.good.job.commons.BaseArouter
import com.good.job.commons.Constants
import com.good.job.commons.arouteJump
import com.good.job.databinding.ActivitySplashBinding

@Route(path = "/models/splash")
class SplashActivity : AppActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash;
    }

    override fun getVMClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java;
    }

    override fun initView() {
        dataBinding?.splashac = this;

        viewModel?.event?.observe(this, Observer {

        })

        viewModel?.data?.observe(this, Observer {
            when(it.requestCode){
                SplashData.CODE_JUMP_MAIN -> {
                    BaseArouter.Builder(Constants.MAIN,this)
                        .isFinish(true)
                        .builder()
                        .start();
                }
            }
        })
    }
}