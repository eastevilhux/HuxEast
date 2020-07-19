package com.good.framework.http

import java.nio.charset.Charset

object HttpConfig {
    const val CODE_SUCCESS = 66;
    const val CODE_ERROR = -1;
    const val CODE_LOGIN = -3;
    const val CODE_NETWORK = 404;
    const val CODE_EMPTY = 44;
    const val CODE_SERVICE_ERROR = -4;
    val HTTP_CHARSET = Charset.forName("UTF-8");
    const val UTF8_CHARSET = "UTF-8";
    const val TIME_OUT = 20L;

    //Retrofit缓存时间为1小时
    const val MAX_AGE = 60

    //请求服务器地址URL
    const val SERVICE_URL = "http://192.168.0.100:8080/lifehouse/"

    //请求Token地址URL
    const val TOKEN_URL = "http://192.168.0.100:8080/token"

}