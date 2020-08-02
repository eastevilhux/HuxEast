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
import org.json.JSONObject
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
        Log.d(TAG,data);
        var json = JSONObject(data);
        var code = json.optInt("code");
        Log.d(TAG,code.toString());
        if(code != HttpConfig.CODE_SUCCESS){
            return adapter.fromJson(data);
        }
        var encryption = json.optBoolean("encryption")?:false;
        Log.d(TAG,encryption.toString());
        if(encryption){
            data = json.optString("data");
            if(data.isEmpty()){
                return adapter.fromJson(data);
            }
            data = URLDecoder.decode(data,HttpConfig.UTF8_CHARSET);
            Constants.serviceKey?.let {
                Log.d(TAG,Constants.decrypType.toString());
                when(Constants.decrypType){
                    Constants.DecrypType.DECRYPT_RSA -> data = RSAUtil.decryptByPublicKey(data,it)
                    Constants.DecrypType.DECRYPT_AES -> data = Constants.serviceKey?.let { AESUtil.aesDecrypt(data, it) };
                }
            }
            Log.d(TAG,data);
            var result = Result<T>();
            result.code = code;
            result.state = json.optBoolean("state");
            result.encryption = encryption;
            result.msg = json.optString("msg");
            result.tag = json.optString("tag");
            val type: Type = object : TypeToken<T>() {}.type
            var t = gson.fromJson<T>(data,type);
            result.data = t;
            val reader = StringReader(gson.toJson(result));
            return adapter.fromJson(reader)
        }else {
            val type: Type = object : TypeToken<Result<T>?>() {}.type
            var result : Result<T> = gson.fromJson(data,type);
            data = result.data.toString();
            data = URLDecoder.decode(data,HttpConfig.UTF8_CHARSET);
            val s = String(Base64Util.decode(data), HttpConfig.HTTP_CHARSET);
            Log.d(TAG+"NO=>",s);
            result.data = gson.fromJson<T>(s,object : TypeToken<T?>() {}.type);
            val reader = StringReader(gson.toJson(result));
            return adapter.fromJson(reader)
        }
    }

}


