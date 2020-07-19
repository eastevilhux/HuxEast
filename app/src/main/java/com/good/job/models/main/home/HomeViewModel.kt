package com.good.job.models.main.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.good.framework.commons.mainThread
import com.good.framework.http.commons.BaseModel
import com.good.framework.http.entity.Error
import com.good.framework.http.entity.Event
import com.good.job.commons.EastViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : EastViewModel<MainData>(application){
    private val baseModel = BaseModel.instance

    val bannerList = MutableLiveData<MutableMap<String,List<String>>>();

    override fun initVMData(): MainData {
        return MainData();
    }

    override fun initOnFragmentActivityCreate() {
        super.initOnFragmentActivityCreate()
        queryBannerList();
    }

    private fun queryBannerList() = GlobalScope.launch{
        var result = baseModel.appBannerList();
        if(result.isSuccess){
            mainThread {
                var list = result.data;
                var map = mutableListOf<Map<String,List<String>>>();
                var tipsList = mutableListOf<String>();
                var urlsList = mutableListOf<String>();
                list?.let {
                    var map = mutableMapOf<String,List<String>>();
                    it.forEach {
                        it.title?.let { it1 -> tipsList.add(it1) };
                        it.image?.let { it1 -> urlsList.add(it1) };
                    }
                    map["tips"] = tipsList;
                    map["urls"] = urlsList;
                    bannerList.value = map;
                }
            }
        }else{
            mainThread {
                error.value = Error(result.code,result.msg?:"");
            }
        }
    }
}