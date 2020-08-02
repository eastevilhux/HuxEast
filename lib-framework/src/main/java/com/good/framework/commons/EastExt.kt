package com.good.framework.commons

import android.os.Looper
import com.good.framework.http.commons.Constants
import com.good.framework.utils.AESUtil
import com.good.framework.utils.JsonUtil
import com.good.framework.utils.RSAUtil
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

fun createRandomNumber(min : Int = 0,max:Int = 10):Int{
    return ((min+Math.random()*(max)).toInt())
}

fun String.encryptData() : String? {
    Constants.serviceKey?.let{
        var data : String? = null;
        when(Constants.encrypType){
            Constants.EncrypType.ENCRYPE_AES->{
                //使用AES加密数据
                data = AESUtil.aesEncrypt(this,it);
            }
            Constants.EncrypType.ENCRYPE_RSA->{
                //使用RSA加密数据
                data = RSAUtil.encryptByPublicKey(this,it);
            }
        }
        return data;
    }?:return this;
}
