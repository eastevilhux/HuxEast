package com.good.framework.http.interceptor

import android.accounts.AccountManager
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HttpInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val headers: Headers = addCommonHeader(request)
        val newRequest = request.newBuilder()
            .headers(headers)
            .build()

        return chain.proceed(newRequest)
    }

    private fun addCommonHeader(request: Request): Headers {
        val builder: Headers.Builder = request.headers().newBuilder()
        return builder.build()
    }
}