package com.good.framework.http.entity

import com.good.framework.http.HttpConfig

class Error {
    var code = 0;
    var msg : String? = null

    constructor()

    constructor(code : Int,msg:String){
        this.code = code;
        this.msg = msg;
    }
}