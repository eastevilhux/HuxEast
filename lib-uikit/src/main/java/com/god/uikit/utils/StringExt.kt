package com.god.uikit.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 判断字符串是否为null,"null","","    "
 * @author hux
 * @version 1.0.0
 * @since 1.0.0
 */
fun String.isEmpty() : Boolean{
    if(this == null)
        return true;
    if("".equals(other = this)){
        return true;
    }
    var result = this.trim();
    if("".equals(result)){
        return true;
    }
    result = result.replace(" ","");
    if("".equals(result)){
        return true;
    }
    if("null".equals(result)){
        return true;
    }
    return false;
}


/**
 * 判断字符串是否为正常的中国大陆手机号码
 * @author hux
 * @since 1.0.0
 * @param haveLine
 *     手机号码中是否包含-或者_也可以视为正确的手机号码
 * @param haveTrim
 *      手机号码中是否包含空格也可视为正确的手机号码
 * @return
 *      是否是正确的手机号码
 */
fun String.isMobile(haveTrim:Boolean = false,haveLine:Boolean = false) : Boolean{
    if(this.isEmpty()){
        return false;
    }
    var temp = this;
    if(haveTrim){
        if(this.indexOf(" ") != -1)
            temp = temp.replace(" ","");
    }
    if(haveLine){
        if(this.indexOf("-") != -1)
            temp = temp.replace("-","");
        if(this.indexOf("_") != -1)
            temp = temp.replace("_","");
    }
    val regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5-9])|(166)|(19[8,9])|)\\d{8}$"
    val p = Pattern.compile(regExp)
    val m = p.matcher(temp)
    return m.matches()
}


/**
 * 判断字符串是否是一个正确的邮箱地址
 * @author hux
 * @since 1.0.0
 * @return
 *      是否是正确的邮箱地址
 */
fun String.isEmail() : Boolean{
    if(this.isEmpty()){
        return false;
    }
    val pat = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+\$";
    val p = Pattern.compile(pat) //实例化Pattern类
    val m: Matcher = p.matcher(this) //验证内容是否合法
    return m.matches();
}

fun String.asteriskMobile(haveTrim:Boolean = false,haveLine:Boolean = false) : String{
    if(!this.isMobile(haveTrim=haveTrim,haveLine = haveLine)){
        return this;
    }
    var temp = this;
    if(haveTrim){
        if(this.indexOf(" ") != -1)
            temp = temp.replace(" ","");
    }
    if(haveLine){
        if(this.indexOf("-") != -1)
            temp = temp.replace("-","");
        if(this.indexOf("_") != -1)
            temp = temp.replace("_","");
    }
    var start = temp.substring(0,3);
    var end = temp.subSequence(temp.length - 4,temp.length);
    return "${start} **** ${end}";
}


fun main() {
    var result = "18896781177".asteriskMobile();
    System.out.println(result);
}