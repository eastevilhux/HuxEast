package com.good.framework.commons

import android.os.Looper
import com.good.framework.utils.JsonUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 转换成json格式
 */
fun Any.toJSON(): String? = JsonUtil.instance.getGson().toJson(this);

/**
 * 当前是否在主线程
 */
fun isMainThread(): Boolean = Looper.myLooper() == Looper.getMainLooper();

/**
 * 通过协程  在主线程上运行
 */
fun mainThread(block: () -> Unit) = GlobalScope.launch(Dispatchers.Main) {
    block()
}
