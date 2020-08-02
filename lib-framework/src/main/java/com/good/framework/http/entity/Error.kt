package com.good.framework.http.entity

import com.good.framework.http.HttpConfig

class Error {
    var code = ERROR_DEFAULT;
    var msg : String? = null
    var type : Int = 0;

    constructor()

    constructor(code : Int,msg:String,type:Int = 0){
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    companion object{
        const val ERROR_DEFAULT = -66;
    }
}