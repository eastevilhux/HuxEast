package com.good.framework.http.entity

import com.good.framework.http.HttpConfig

class Result<T>{
    var code = 0;
    var data : String? = null;
    var tag = null;
    var msg : String? = null;

    val isSuccess
        get() = code == HttpConfig.CODE_SUCCESS;

    companion object{
        fun serviceError(code:Int = HttpConfig.CODE_ERROR) : Result<*>{
            val result = Result<Any>()
            result.code = code
            return result;
        }

        fun exception(data:String?,message : String?,code:Int = HttpConfig.CODE_ERROR) : Result<*>{
            var result = Result<Any>();
            result.code = code;
            result.msg = message;
            result.data = data;
            return result;
        }
    }
}