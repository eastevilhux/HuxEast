package com.good.framework.http.retrofit.adapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EastCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type?,
        annotations: Array<out Annotation>?,
        retrofit: Retrofit?
    ): CallAdapter<*, *> {
        return EastCallAdapter<Any>(returnType!!);
    }

    companion object{
        fun create() : EastCallAdapterFactory = EastCallAdapterFactory();
    }
}