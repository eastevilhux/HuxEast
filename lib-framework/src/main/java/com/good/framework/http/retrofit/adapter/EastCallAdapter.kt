package com.good.framework.http.retrofit.adapter

import android.util.Log
import com.good.framework.http.HttpConfig
import com.good.framework.http.entity.Result
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class EastCallAdapter<R>(private val type:Type) : CallAdapter<R, R>{
    val TAG = "EastCallAdapter=>";

    override fun adapt(call: Call<R>): R {
        return try {
            var response = call.execute();
            Log.d(TAG+"code:",response.code().toString());
            Log.d(TAG+"msg:",response.message());
            if(response.isSuccessful){
                Log.d(TAG,"http reauest sucess");
                var body = response.body() ?: emptyResponse();
                val result = body as Result<*>;
                if(result.code != HttpConfig.CODE_SUCCESS){
                    when(result.code){
                        HttpConfig.CODE_LOGIN -> loginError()
                        else->errorResponse(response)
                    }
                }
                return body;
            }else{
                errorResponse(response);
            }
        }catch (e: Exception){
            parseException(e)
        }
    }

    override fun responseType(): Type = type;

    private fun loginError(){
        Log.d(TAG,"login_error");
    }

    private fun errorResponse(response: Response<R>) : R{
        Log.d(TAG,"errorResponse");
        return error(Result<Any>(),response) as R;
    }

    private fun emptyResponse() : R{
        Log.d(TAG,"emptyResponse");
        return empty(Result<Any>()) as R;
    }

    private fun parseException(e:Exception) : R{
        Log.d(TAG,e?.let { e.message }?:"no error message")
        return when(e){
            is IOException,
            is ConnectException,
            is UnknownHostException,
            is SocketTimeoutException -> networkError()
            else -> exceptionResponse(e.message);
        }
    }

    private fun networkError() : R{
        Log.d(TAG,"networkError");
        return exception(Result<Any>(),"network_error",HttpConfig.CODE_NETWORK) as R;
    }

    private fun exceptionResponse(message: String?) : R{
        return exception(Result<Any>(),message,HttpConfig.CODE_SERVICE_ERROR) as R;
    }

    companion object{

        fun <T : Result<*>> empty(response: T, code: Int = HttpConfig.CODE_EMPTY): T {
            response.code = code
            response.msg = "no_data";
            return response
        }

        fun <T : Result<*>> error(response: T, retrofitResponse: Response<*>): T {
            response.code = retrofitResponse.code()
            response.msg = retrofitResponse.message()
            return response
        }

        fun <T : Result<*>> exception(
            response: T,
            message: String?,
            code: Int = HttpConfig.CODE_ERROR
        ): Result<*> {
            response.code = code
            response.msg = message
            return response
        }
    }
}