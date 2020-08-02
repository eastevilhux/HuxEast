package com.good.framework.http.interceptor

import com.good.framework.http.commons.Constants
import com.lzy.okgo.model.HttpHeaders
import okhttp3.*
import java.io.IOException

class ParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        val urlBuilder = request.url().newBuilder()
        val headers: Headers = addCommonHeader(request)

        var keytype : String? = null;
        Constants.serviceType?.let {
            when(Constants.serviceType){
                Constants.ServiceType.SERVICE_TYPE_AES->keytype = "2"
                Constants.ServiceType.SERVICE_TYPE_RSA->keytype = "1"
            }
        }
        var userid = Constants.userid;
        var appkey =  Constants.appKey;

        if (METHOD_GET == request.method()) { // GET方法
            // 这里可以添加一些公共get参数
            appkey?.let {
                urlBuilder.addEncodedQueryParameter("appkey", it);
            }
            keytype?.let {
                urlBuilder.addEncodedQueryParameter("keytype", it);
            }
            userid?.let {
                urlBuilder.addEncodedQueryParameter("userid", it);
            }

            val httpUrl = urlBuilder.build()
            // 将最终的url填充到request中
            requestBuilder.headers(headers)
                .url(httpUrl)
        } else if (METHOD_POST == request.method()) { // POST方法
            // FormBody和url不太一样，若需添加公共参数，需要新建一个构造器
            val bodyBuilder = FormBody.Builder()
            // 把已有的post参数添加到新的构造器
            if (request.body() is FormBody) {
                val formBody = request.body() as FormBody?
                for (i in 0 until formBody!!.size()) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i))
                }
            }

            // 这里可以添加一些公共post参数
            appkey?.let {
                bodyBuilder.addEncoded("appkey", it)
            }
            keytype?.let {
                bodyBuilder.addEncoded("keytype", it)
            }
            userid?.let {
                bodyBuilder.addEncoded("userid", it);
            }
            val newBody = bodyBuilder.build()

            // 将最终的表单body填充到request中
            requestBuilder
                .headers(headers)
                .post(newBody)
        }
        return chain.proceed(requestBuilder.build())
    }

    private fun addCommonHeader(request: Request): Headers {
        val builder: Headers.Builder = request.headers().newBuilder()
        return builder.build()
    }

    companion object {
        const val METHOD_GET = "GET"
        const val METHOD_POST = "POST"
        const val HEADER_KEY_USER_AGENT = "User-Agent"
    }
}