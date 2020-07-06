package com.good.job.models.main.home

import android.util.Log
import cn.bingoogolapple.bgabanner.BGABanner

class Test {
    fun test() {
        val bgaBanner: BGABanner? = null
        bgaBanner!!.setAdapter { banner, itemView, model, position -> Log.d("fuck", "fuck") }
    }
}