package com.good.framework.http.commons

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class CustomGsonConverterFactory(private val gson: Gson) : Converter.Factory(){

    fun create() : CustomGsonConverterFactory{
        return create(Gson())
    }

    fun create(gson: Gson) : CustomGsonConverterFactory{
        if(gson == null)
            throw NullPointerException("the gson is null");
        return CustomGsonConverterFactory(gson);
    }


    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody?, *>? {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        //return CustomGsonResponseConverter(gson, adapter)
    }

    override fun requestBodyConverter(
        type: Type?,
        parameterAnnotations: Array<Annotation?>?,
        methodAnnotations: Array<Annotation?>?,
        retrofit: Retrofit?
    ): Converter<*, RequestBody?>? {
        val adapter: TypeAdapter<*> = gson.getAdapter(TypeToken.get(type))
        //return GsonRequestBodyConverter(gson, adapter)
    }

}