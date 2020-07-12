package com.good.framework.http.commons

import com.good.framework.entity.KeySet
import com.good.framework.http.RetrofitFactory
import com.good.framework.http.entity.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel {
    private val baseService = RetrofitFactory.instance.baseService;

    suspend fun appSplashEvent() : Result<KeySet> = withContext(Dispatchers.IO) {
        return@withContext baseService.appBeforehand();
    }


    companion object{
        val instance : BaseModel by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            BaseModel();
        }
    }
}