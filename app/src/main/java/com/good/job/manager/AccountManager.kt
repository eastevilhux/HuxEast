package com.good.job.manager

import com.good.framework.http.entity.User

class AccountManager private constructor(){
    var user : User? = null;
    companion object{
        val instance : AccountManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AccountManager();
        }
    }

    fun isLogin() : Boolean{
        return user != null && user?.id != 0
    }

}