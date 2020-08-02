package com.good.job.models.main.home

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.god.uikit.utils.StatusBarUtil
import com.good.job.R
import com.good.job.commons.AppFragment
import com.good.job.commons.BaseArouter
import com.good.job.commons.Constants
import com.good.job.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : AppFragment<FragmentHomeBinding, HomeViewModel>(){

    override fun getLayoutRes(): Int = R.layout.fragment_home;

    override fun getVMClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java;
    }

    @SuppressLint("CheckResult")
    override fun initView() {
        super.initView()

        dataBinding?.fragment = this;

        val options = RequestOptions()
        options.placeholder(R.drawable.icon_home_banner_default)
            .error(R.drawable.icon_home_banner_default)

        dataBinding.bannerGuideContent.setAdapter { banner, itemView, model, position ->
            Glide.with(getActivity()!!)
                .load(model)
                .apply(options)
                .into(itemView!! as ImageView)
        }

        viewModel.bannerList.observe(this, Observer {
            dataBinding.bannerGuideContent.setData(it["tips"],it["urls"]);
        })
    }


    fun toInquiry(view : View){
        BaseArouter.Builder(Constants.INQUIRY,getAppActivity())
            .builder().start();
    }


}