package com.good.framework.http.commons

import com.good.framework.commons.encryptData
import com.good.framework.commons.toJSON
import com.good.framework.entity.KeySet
import com.good.framework.http.RetrofitFactory
import com.good.framework.http.entity.Event
import com.good.framework.http.entity.Result
import com.good.framework.http.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel {
    private val baseService = RetrofitFactory.instance.baseService;

    suspend fun appBeforehand() : Result<KeySet> = withContext(Dispatchers.IO) {
        return@withContext baseService.appBeforehand();
    }

    fun appSplashEvent() : Result<Event> {
        return baseService.appSplashEvent(Event.TYPE_APP_SPLASH);
    }

    fun appBannerList() : Result<List<Event>>{
        return baseService.appBannerList(Event.TYPE_HOME_BANNER);
    }

    fun userLogin(account:String,password:String) : Result<User>{
        var _account = account.encryptData()?:"";
        var _password = password.encryptData()?:"";
        return baseService.userLogin(_account,_password);
    }

    fun cacheUserInfo() : Result<User>{
        return baseService.cacheUser();
    }

    companion object{
        val instance : BaseModel by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            BaseModel();
        }
    }
}