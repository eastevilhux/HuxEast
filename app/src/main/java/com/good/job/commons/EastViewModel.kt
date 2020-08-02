package com.good.job.commons

import android.app.Application
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.good.framework.commons.BaseViewModel
import com.good.framework.commons.isMainThread
import com.good.framework.commons.mainThread
import com.good.framework.entity.VMData
import com.good.framework.http.HttpConfig
import com.good.framework.http.entity.Error;
import com.good.job.manager.AccountManager

abstract open class EastViewModel<VM : VMData>(application: Application) : BaseViewModel(application) {
    var data: MutableLiveData<VM>;

    var error : MutableLiveData<Error>;

    var loading = MutableLiveData<Boolean>();

    init {
        data = MutableLiveData();
        data.value = initVMData();
        data.value!!.code = VMData.Code.CODE_DEFAULT;
        loading.value = false;

        var er = Error();
        er.code = Error.ERROR_DEFAULT;
        error = MutableLiveData();
        error.value = er;
    }

    abstract fun initVMData() : VM;

    open fun error(code : Int = HttpConfig.CODE_ERROR,msg : String){
        var er = Error();
        er.code = code;
        er.msg = msg;
        if(isMainThread()) {
            error.value = er;
        }else{
            mainThread {
                error.value = er;
            }
        }
    }

    fun getString(@StringRes strRes:Int):String{
        return getApplication<Application>().getString(strRes);
    }

    fun getColor(@ColorRes colorRes: Int) : Int{
        return getApplication<Application>().getColor(colorRes);
    }

    fun postValue(vmData: VM){
        data.value = vmData;
    }

    fun isLogin() : Boolean{
        return AccountManager.instance.isLogin();
    }

    fun showLoading(){
        if(isMainThread()){
            loading.value = true;
        }else{
            mainThread {
                loading.value = true;
            }
        }
    }

    fun dismissLoading(){
        if(isMainThread()){
            loading.value = false;
        }else{
            mainThread {
                loading.value = false;
            }
        }
    }


}