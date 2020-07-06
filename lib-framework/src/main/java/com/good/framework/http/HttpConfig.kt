package com.good.framework.http

import java.nio.charset.Charset

object HttpConfig {
    const val CODE_SUCCESS = 88;
    const val CODE_ERROR = -1;
    const val CODE_LOGIN = -3;
    val CHAR_SET = Charset.forName("UTF-8");
    const val TIME_OUT = 5L;

    //Retrofit缓存时间为1小时
    const val MAX_AGE = 60
}