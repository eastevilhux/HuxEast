package com.god.uikit.utils

import android.content.Context

class ViewUtil{
    companion object{
        fun dip2px(content:Context,dipValue :Int): Int {
            var scale = content.resources.displayMetrics.density;
            return (dipValue * scale+ 0.5f).toInt();
        }

        fun px2dip(content: Context,pxValue : Int): Int {
            var scale = content.resources.displayMetrics.density;
            return (pxValue / scale + 0.5f).toInt();
        }

        fun getScreenSize(content: Context) : Array<Int> {
            var dm = content.resources.displayMetrics;
            return arrayOf(dm.widthPixels,dm.heightPixels);
        }
    }
}