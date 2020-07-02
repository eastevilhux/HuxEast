package com.good.framework.commons

import androidx.lifecycle.LifecycleObserver

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

interface ILifecycleObserver : LifecycleObserver{

    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(owner: LifecycleOwner?);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner?);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChange(owner: LifecycleOwner?);

}