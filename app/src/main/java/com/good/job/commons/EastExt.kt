package com.good.job.commons

import com.alibaba.android.arouter.launcher.ARouter
import com.good.job.entity.Param
import retrofit2.CallAdapter

fun arouteJump(path:String,vararg params:Param,finish : Boolean = false, activity: AppActivity<*,*>? = null){
    var postcard = ARouter.getInstance().build(path);
    params.forEach {
        var p = it;
        it.intVlaue?.let {
            postcard.withInt(p.key,it);
        }
        it.strValue?.let {
            postcard.withString(p.key,it);
        }
        it.doubleValue?.let {
            postcard.withSerializable(p.key,it);
        }
        it.serValue?.let {
            postcard.withSerializable(p.key,it);
        }
    }
    postcard.navigation(activity)
    if(activity != null && finish){
        activity.finish()
    }
}


