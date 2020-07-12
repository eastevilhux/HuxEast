package com.good.framework.http.retrofit.convert

import android.annotation.SuppressLint
import android.util.Log
import com.good.framework.http.HttpConfig
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Converter
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
        var result = value.string();
        /*Log.d(TAG,result);
        var json = JSONObject(result);
        var data = json.optString("data");
        Log.d(TAG,data);
        data?.let {
            data = URLDecoder.decode(data,HttpConfig.UTF8_CHARSET);
        }
        var map = HashMap<String,Any?>();
        map["code"] = json.optInt("code");
        map["msg"] = json.optString("msg");
        map["data"] = data;
        map["size"] = json.optInt("size");
        map["tag"] = json.optString("tag");
        map["extended"] = json.optString("extended");
        result = gson.toJson(map);
        Log.d(TAG,result);*/
        value.use {
            return adapter.fromJson(result)
        }

    }

}