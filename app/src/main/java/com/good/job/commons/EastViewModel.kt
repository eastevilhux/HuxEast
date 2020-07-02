package com.good.job.commons

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.good.framework.commons.BaseViewModel
import com.good.framework.entity.VMData

abstract open class EastViewModel<VM : VMData>(application: Application) : BaseViewModel(application) {
    var data: MutableLiveData<VM>;

    init {
        data = MutableLiveData();
        data.value = initVMData();
        data.value!!.code = VMData.Code.CODE_DEFAULT;
        initModel();
    }

    abstract fun initVMData() : VM;

    open fun initModel(){

    }

    fun getLiveData() : MutableLiveData<VM>{
        return data;
    }

    fun getData() : VM? {
        return data?.value;
    }
}