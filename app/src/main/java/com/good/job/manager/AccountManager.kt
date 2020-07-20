package com.good.job.manager

class AccountManager private constructor(){
    companion object{
        val instance : AccountManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AccountManager();
        }
        var iconUrl : String? = null;
        var userName : String? = null;
        var sex : Int = 0;
        var userId : Int = 0;
    }

    fun isLogin() : Boolean{
        return userId > 0;
    }
}