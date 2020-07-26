package com.good.job.models.main.user

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.good.framework.commons.BaseFragment
import com.good.job.R
import com.good.job.commons.Constants
import com.good.job.databinding.FragmentUserBinding

class UserFragment : BaseFragment<FragmentUserBinding,UserViewModel>(){

    override fun getLayoutRes(): Int = R.layout.fragment_user;

    override fun getVMClass(): Class<UserViewModel> {
        return UserViewModel::class.java;
    }

    override fun initView() {
        super.initView()
        dataBinding.fragment = this;
    }


    fun userInfoClick(view : View){
        if(viewModel?.isLogin()){
            //用户已登录，进入详情页面
        }else{
            //跳转进入登录界面
            ARouter.getInstance().build(Constants.LOGIN)
                .navigation();
        }
    }

}