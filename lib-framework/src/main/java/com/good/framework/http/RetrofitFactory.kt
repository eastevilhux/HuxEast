package com.good.framework.http

import com.good.framework.http.commons.CustomGsonConverterFactory
import com.good.framework.http.interceptor.HttpInterceptor
import com.good.framework.http.interceptor.LogInterceptor
import com.good.framework.http.interceptor.NetInterceptor
import com.good.framework.http.interceptor.ParamsInterceptor
import com.good.framework.http.retrofit.adapter.EastCallAdapterFactory
import com.good.framework.http.retrofit.convert.EastConverterFactory
import com.good.framework.http.service.BaseService
import com.good.framework.utils.JsonUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor(){
    companion object{
        val instance : RetrofitFactory by lazy(mode=  LazyThreadSafetyMode.SYNCHRONIZED){
            RetrofitFactory();
        }
    }

    val baseService : BaseService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(HttpInterceptor())
            .addInterceptor(LogInterceptor())
            .addNetworkInterceptor(HttpInterceptor())
            .build();

        val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(HttpConfig.SERVICE_URL)
            .addConverterFactory(EastConverterFactory.create(JsonUtil.instance.getGson())) //添加gson转换器
            .addCallAdapterFactory(EastCallAdapterFactory()) //添加rxjava转换器
            .client(okHttpClient)
            .build()

        mRetrofit.create(BaseService::class.java)
    }
}
