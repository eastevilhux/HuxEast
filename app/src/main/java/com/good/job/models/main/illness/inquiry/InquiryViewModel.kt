package com.good.job.models.main.illness.inquiry

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.good.job.commons.EastViewModel

class InquiryViewModel(application: Application) : EastViewModel<IllnessData>(application) {

    companion object{
        const val SEX_BOY = 2;
        const val SEX_GIRL = 1;
    }

    val sex = MutableLiveData<Int>();

    val haveGenecinfo = MutableLiveData<Boolean>();

    override fun initVMData(): IllnessData = IllnessData();

    override fun initModel() {
        super.initModel()
        sex.value = SEX_GIRL;
        haveGenecinfo.value = false;
    }

    fun changeSex(type:Int){
        sex.value = type;
    }

    fun chooseGenecinfo(){
        if(haveGenecinfo.value == true){
            haveGenecinfo.value = false;
        }else{
            haveGenecinfo.value = true;
        }
    }
}