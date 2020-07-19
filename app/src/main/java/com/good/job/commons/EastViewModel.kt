package com.good.job.commons

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.good.framework.commons.BaseViewModel
import com.good.framework.entity.VMData
import com.good.framework.http.HttpConfig
import com.good.framework.http.entity.Error;

abstract open class EastViewModel<VM : VMData>(application: Application) : BaseViewModel(application) {
    var data: MutableLiveData<VM>;

    var error : MutableLiveData<Error>;

    init {
        data = MutableLiveData();
        data.value = initVMData();
        data.value!!.code = VMData.Code.CODE_DEFAULT;

        var er = com.good.framework.http.entity.Error();
        er.code = 0;
        error = MutableLiveData();
        error.value = er;
    }

    abstract fun initVMData() : VM;

    /*suspend fun <T> requestResponse(
        loading: Loading? = Loading.shortToast(),
        error: Error? = Error.shortToast(),
        bloak:suspend() -> Result<T>
    ):Result<T>{

    }*/

    open fun error(code : Int = HttpConfig.CODE_ERROR,msg : String){
        var er = Error();
        er.code = code;
        er.msg = msg;
        error.value = er;
    }

    fun postValue(vmData: VM){
        data.value = vmData;
    }
}