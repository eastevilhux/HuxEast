package com.good.framework.http.interceptor

import android.util.Log
import com.good.framework.http.commons.Constants
import com.good.framework.http.HttpConfig
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.lang.String
import java.nio.charset.Charset

class HttpInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        val headers: Headers = addCommonHeader(oldRequest)

        val commonParamsUrlBuilder = oldRequest.url()
            .newBuilder()
            .scheme(oldRequest.url().scheme())
            .host(oldRequest.url().host());

        val newRequestBuild = oldRequest.newBuilder()
            .method(oldRequest.method(), oldRequest.body())
            .headers(headers)
            .url(commonParamsUrlBuilder.build())

        //拿到响应体
        val mResponse = chain.proceed(newRequestBuild.build())
        val responseBody = mResponse.body()

        //得到缓冲源

        //得到缓冲源
        val source = responseBody!!.source()

        //请求全部
        source.request(Long.MAX_VALUE) // Buffer the entire body.

        val buffer = source.buffer()
        var charset: Charset = HttpConfig.HTTP_CHARSET

        val contentType = responseBody.contentType()

        if (contentType != null) {
            charset = contentType.charset(HttpConfig.HTTP_CHARSET)!!
        }
        //读取返回数据
        val bodyString = buffer.clone().readString(charset)
        return mResponse
    }

    private fun addCommonHeader(request: Request): Headers {
        val builder: Headers.Builder = request.headers().newBuilder();

        var keytype : kotlin.String? = null;
        Constants.serviceType?.let {
            when(Constants.serviceType){
                Constants.ServiceType.SERVICE_TYPE_AES->keytype = "2"
                Constants.ServiceType.SERVICE_TYPE_RSA->keytype = "1"
            }
        }
        var userid = Constants.userid;
        var appkey =  Constants.appKey;
        appkey?.let {
            builder.add("appkey", it);
        }
        keytype?.let {
            builder.add("keytype", it);
        }
        userid?.let {
            builder.add("userid", it);
        }
        return builder.build()
    }
}