package com.good.framework.commons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.auction.framework.utils.AutoDisposeUtil
import com.uber.autodispose.AutoDisposeConverter

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),ILifecycleObserver{
    lateinit var mLifecycleOwner :LifecycleOwner;

    override fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }

    override fun onCreate(owner: LifecycleOwner?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy(owner: LifecycleOwner?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLifecycleChange(owner: LifecycleOwner?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    protected fun <T> bindLifecycle() : AutoDisposeConverter<T> {
        return AutoDisposeUtil.bindLifecycle(mLifecycleOwner!!)
    }

    open fun initModel(){

    }

    open fun initOnFragmentActivityCreate(){
        
    }

    open fun onStart(){

    }

    open  fun onResume(){

    }

    open fun onStop(){

    }

    open fun onPause(){

    }

    open fun onRestart(){

    }

}