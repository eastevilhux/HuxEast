package com.good.framework.http.service

import com.good.framework.entity.KeySet
import com.good.framework.http.entity.Result
import retrofit2.http.POST
import retrofit2.http.Query


interface BaseService {
    companion object{
        val BASE_URL : String
            get() = "http://192.168.0.100:8080/lifehouse/";
    }


    @POST("life/appbeforehand")
    fun appBeforehand(): Result<KeySet>;

    /**
     * 查询App欢迎页正在进行中的活动信息
     *
     * create by hux at 2020/4/2 1:34
     * @author hux
     * @since 1.0.0
     * @param apptype
     * app类型，默认为2
     * @return
     * [<]
     */
    @POST("event/appsplash")
    fun appSplashEvent(@Query("apptype") apptype: Int): Result<*>;

}