package com.good.job.models.main

import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.god.uikit.utils.StatusBarUtil
import com.god.uikit.widget.BottomLayout.OnBottomClickListener
import com.good.job.R
import com.good.job.commons.AppActivity
import com.good.job.commons.Constants
import com.good.job.databinding.ActivityMainBinding
import com.good.job.models.main.User.UserFragment
import com.good.job.models.main.auction.AuctionFragment
import com.good.job.models.main.commodity.CommlistFragment
import com.good.job.models.main.home.HomeFragment


@Route(path = Constants.MAIN)
class MainActivity : AppActivity<ActivityMainBinding, MainViewModel>(), OnBottomClickListener {

    var fragmentList = mutableListOf<Fragment>();

    lateinit var adapter: FragmentPagerAdapter;

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun getVMClass(): Class<MainViewModel> {
        return MainViewModel::class.java;
    }

    override fun initView() {
        dataBinding?.bottomLayout!!.setOnBottomClickListener(this);
        initFragmentList();

        adapter = object : FragmentPagerAdapter(supportFragmentManager, 1) {

            override fun getItem(position: Int): Fragment {
                return fragmentList[position];
            }

            override fun getCount(): Int {
                return fragmentList.size;
            }
        }
        dataBinding?.adapter = adapter;
    }

    override fun isImmersion(): Boolean = true;


    override fun onBottomClick(position: Int) {
        dataBinding?.fragmentViewpager!!.currentItem = position-1;
        when(position){
            1 -> {
                StatusBarUtil.setRootViewFitsSystemWindows(this, false)
                StatusBarUtil.setStatusBarColor(this, 0x00FFFFFF)
            }
            4 -> {
                StatusBarUtil.setRootViewFitsSystemWindows(this, false)
                StatusBarUtil.setStatusBarColor(this, 0x00FFFFFF)
            }
        }
    }

    fun initFragmentList(){
        fragmentList.add(HomeFragment());
        fragmentList.add(CommlistFragment());
        fragmentList.add(AuctionFragment());
        fragmentList.add(UserFragment());
    }
}