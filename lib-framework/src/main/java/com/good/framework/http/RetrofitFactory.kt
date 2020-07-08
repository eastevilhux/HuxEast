package com.good.framework.http

import com.good.framework.http.interceptor.HttpInterceptor
import com.good.framework.http.interceptor.NetInterceptor
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

    init {
        var okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(HttpInterceptor())
            .addNetworkInterceptor(NetInterceptor())
            .build();

        /*val mRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(HttpConfig.SERVICE_URL)
            .addConverterFactory(
                CustomGsonConverterFactory.create(
                    JsonUtil.getInstance().getGson()
                )
            ) //添加gson转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加rxjava转换器
            .client(okHttpClient)
            .build()*/
    }
}
