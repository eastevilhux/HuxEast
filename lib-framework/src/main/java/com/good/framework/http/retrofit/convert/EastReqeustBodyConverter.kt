package com.good.framework.http.retrofit.convert

import android.annotation.SuppressLint
import android.util.Log
import com.good.framework.http.HttpConfig
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter

class EastReqeustBodyConverter<T>(private val gson:Gson,val adapter: TypeAdapter<T>) : Converter<T,RequestBody>{

    companion object{
        private val MEDIA_TYPE: MediaType? = MediaType.parse("application/json; charset=UTF-8");
        private const val TAG = "EastReqeustBodyConverter==>";
    }


    @SuppressLint("LongLogTag")
    override fun convert(value: T): RequestBody {
        val buffer = Buffer();
        val writer = OutputStreamWriter(buffer.outputStream(),HttpConfig.HTTP_CHARSET);
        val jsonWriter = gson.newJsonWriter(writer)
        var s = value.toString();
        Log.d(TAG,s);
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        var temp = buffer.readByteString();
        Log.d(TAG,temp.toString());
        return RequestBody.create(MEDIA_TYPE, temp);
    }

}