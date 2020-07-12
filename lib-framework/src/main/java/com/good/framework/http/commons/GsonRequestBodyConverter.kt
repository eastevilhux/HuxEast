package com.good.framework.http.commons

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import kotlin.text.Charsets.UTF_8


class GsonRequestBodyConverter<T>(val gson: Gson?,val adapter:TypeAdapter<T>) : Converter<T, RequestBody>{

    private val MEDIA_TYPE: MediaType? = MediaType.parse("application/json; charset=UTF-8")

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody? {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson!!.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
    }
}