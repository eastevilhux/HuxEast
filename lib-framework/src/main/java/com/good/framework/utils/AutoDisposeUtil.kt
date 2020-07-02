package com.auction.framework.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider


class AutoDisposeUtil {
    companion object{
        fun <T> bindLifecycle(owner: LifecycleOwner) : AutoDisposeConverter<T> {
            return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner,Lifecycle.Event.ON_DESTROY));
        }
    }
}