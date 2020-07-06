package com.good.framework.http.retrofit.convert

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.NullPointerException
import java.lang.reflect.Type

class EastConverterFactory(private val gson:Gson) : Converter.Factory(){

    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *> {
        var adapter = gson.getAdapter(TypeToken.get(type));
        return EastResponseBodyConverter(gson,adapter);
    }

    override fun requestBodyConverter(
        type: Type?,
        parameterAnnotations: Array<out Annotation>?,
        methodAnnotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return EastReqeustBodyConverter(gson,adapter);
    }

    companion object{
        private  val TAG = "EastConverterFactory=>";

        fun create() : EastConverterFactory{
            return create(Gson());
        }

        fun create(gson:Gson?) : EastConverterFactory{
            if(gson == null) {
                Log.e(TAG,"create EastConverterFactory when gson is null")
                throw NullPointerException("gson not allow null")
            };
            return EastConverterFactory(gson);
        }
    }
}