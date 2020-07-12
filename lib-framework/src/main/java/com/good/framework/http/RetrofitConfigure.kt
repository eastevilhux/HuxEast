package com.good.framework.http

import com.good.framework.http.interceptor.HttpInterceptor
import com.good.framework.http.retrofit.adapter.EastCallAdapterFactory
import com.good.framework.http.retrofit.convert.EastConverterFactory
import com.good.framework.http.service.BaseService
import com.good.framework.utils.JsonUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import java.io.IOException

class RetrofitConfigure private constructor(){

    private val netInterceptor: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response? {
            val request: Request = chain.request()
            val response: Response = chain.proceed(request)
            return response.newBuilder()
                .removeHeader("Pragma") // 清除头信息
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=" + HttpConfig.MAX_AGE)
                .build()
        }
    }

    val baseService : BaseService by lazy {
        val okBuilder = OkHttpClient.Builder()
            .connectTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(HttpConfig.TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(netInterceptor)
            .addInterceptor(HttpInterceptor());

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseService.BASE_URL)
            .client(okBuilder.build())
            .addConverterFactory(EastConverterFactory.create(JsonUtil.instance.getGson()))
            .addCallAdapterFactory(EastCallAdapterFactory())
            .build()

        retrofit.create(BaseService::class.java)
    }


    companion object{

        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitConfigure() }
    }

}