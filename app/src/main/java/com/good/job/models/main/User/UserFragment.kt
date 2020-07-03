package com.good.job.models.main.User

import com.good.framework.commons.BaseFragment
import com.good.job.R
import com.good.job.databinding.FragmentUserBinding

class UserFragment : BaseFragment<FragmentUserBinding,UserViewModel>(){

    override fun getLayoutRes(): Int = R.layout.fragment_user;

    override fun getVMClass(): Class<UserViewModel> {
        return UserViewModel::class.java;
    }

}