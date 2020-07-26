package com.good.job.models.main.user

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.good.framework.http.entity.User
import com.good.job.commons.EastViewModel
import com.good.job.manager.AccountManager

class UserViewModel(application: Application) : EastViewModel<UserData>(application){
    private val accountManager = AccountManager.instance;

    val user = MutableLiveData<User>();

    val isLogin = MutableLiveData<Boolean>();

    override fun initVMData(): UserData {
        return UserData();
    }

    override fun initModel() {
        super.initModel()
        isLogin.value = accountManager.isLogin();
        user.value = accountManager.user;
    }


}