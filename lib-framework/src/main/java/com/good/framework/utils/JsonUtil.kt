package com.good.framework.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.lang.Exception

class JsonUtil private constructor(){

    companion object{
        private val gson : Gson by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED ){
            val gsonBuilder = GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                gsonBuilder.create();

        }

        val instance : JsonUtil by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){
            JsonUtil();
        }
    }

    fun getGson() : Gson{
        return gson;
    }


    fun toJson(str:String) : String{
        if(str.isEmpty())
            return str;
        return gson.toJson(str);
    }

    fun isJson(str : String) : Boolean{
        if(str.isEmpty())
            return false;
        try {
            JsonParser().parse(str);
            return true;
        }catch (e : Exception){
            return false;
        }
    }
}