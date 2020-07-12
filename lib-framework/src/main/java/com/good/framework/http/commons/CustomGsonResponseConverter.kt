package com.good.framework.http.commons

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException


class CustomGsonResponseConverter<T>(val gson: Gson?,val adapter: TypeAdapter<T>?) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        return try {
            adapter!!.fromJson(value.string())
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        } finally {
            value.close()
        }
    }
}