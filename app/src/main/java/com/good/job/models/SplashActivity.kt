package com.good.job.models

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.good.framework.commons.BaseActivity
import com.good.job.R
import com.good.job.commons.Constants
import com.good.job.databinding.ActivitySplashBinding
import com.good.job.viewmodels.SplashViewModel

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
    }

    fun onViewClick(view: View?){
        when (view?.id){
            R.id.testview -> ARouter.getInstance().build(Constants.MAIN).navigation();
        }
    }

}