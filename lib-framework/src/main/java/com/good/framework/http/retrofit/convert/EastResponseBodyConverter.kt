package com.good.framework.http.retrofit.convert

import android.annotation.SuppressLint
import android.util.Log
import com.good.framework.http.HttpConfig
import com.good.framework.http.commons.Constants
import com.good.framework.http.entity.Result
import com.good.framework.utils.AESUtil
import com.good.framework.utils.Base64Util
import com.good.framework.utils.RSAUtil
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.StringReader
import java.lang.reflect.Type
import java.net.URLDecoder

class EastResponseBodyConverter<T> internal constructor(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {
    companion object {
        private const val TAG = "EastResponseBodyConverter==>";
    }

    @SuppressLint("LongLogTag")
    override fun convert(value: ResponseBody): T {
        var data = value.string();
        val type: Type = object : TypeToken<Result<T>?>() {}.type
        var result : Result<T> = gson.fromJson(data,type);
        Log.d(TAG,result.encryption.toString());
        if(result.encryption){
            data = result.data.toString();
            data = URLDecoder.decode(data,HttpConfig.UTF8_CHARSET);
            Log.d(TAG,data);
            Constants.serviceKey?.let {
                Log.d(TAG,Constants.decrypType.toString());
                when(Constants.decrypType){
                    Constants.DecrypType.DECRYPT_RSA -> data = RSAUtil.decryptByPublicKey(data,it)
                    Constants.DecrypType.DECRYPT_AES -> data = Constants.serviceKey?.let { AESUtil.aesDecrypt(data, it) };
                }
            }
            result.data = gson.fromJson<T>(data,object : TypeToken<T?>() {}.type);
            val reader = StringReader(gson.toJson(result));
            return adapter.fromJson(reader)
        }else {
            data = result.data.toString();
            data = URLDecoder.decode(data,HttpConfig.UTF8_CHARSET);
            val s = String(Base64Util.decode(data), HttpConfig.HTTP_CHARSET);
            result.data = gson.fromJson<T>(s,object : TypeToken<T?>() {}.type);
            val reader = StringReader(gson.toJson(result));
            return adapter.fromJson(reader)
        }
    }

}


