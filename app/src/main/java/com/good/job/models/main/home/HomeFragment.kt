package com.good.job.models.main.home

import android.annotation.SuppressLint
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.good.framework.commons.BaseFragment
import com.good.job.R
import com.good.job.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(){

    override fun getLayoutRes(): Int = R.layout.fragment_home;

    override fun getVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java;
    }

    @SuppressLint("CheckResult")
    override fun initView() {
        super.initView()

        val options = RequestOptions()
        options.placeholder(R.drawable.icon_home_banner_default)
            .error(R.drawable.icon_home_banner_default)

        banner_guide_content.setAdapter { banner, itemView, model, position ->
            Glide.with(activity!!)
                .load(model)
                .apply(options);
        }
    }
}