package com.good.framework.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

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

}