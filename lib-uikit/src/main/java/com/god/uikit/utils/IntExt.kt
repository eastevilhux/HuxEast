package com.god.uikit.utils

import android.content.res.Resources

fun Int.dip2Px() : Int{
    val scale = Resources.getSystem().displayMetrics.density
    return (this * scale + 0.5f).toInt();
}

fun Int.sp2px(): Int {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (this * fontScale + 0.5f).toInt()
}

fun Int.px2dp(): Int {
    val scale = Resources.getSystem().displayMetrics.density
    return (this / scale + 0.5f).toInt()
}

fun Int.px2sp(): Int {
    val fontScale = Resources.getSystem().displayMetrics.scaledDensity
    return (this / fontScale + 0.5f).toInt()
}