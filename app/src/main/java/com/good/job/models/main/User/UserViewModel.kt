package com.good.job.models.main.User

import android.app.Application
import com.good.job.commons.EastViewModel

class UserViewModel(application: Application) : EastViewModel<UserData>(application){
    override fun initVMData(): UserData {
        return UserData();
    }

}