package com.good.framework.commons

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<D : ViewDataBinding,V : BaseViewModel> : AppCompatActivity(){
    var dataBinding : D? = null;
    var viewModel:V? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,getLayoutRes())
        var vp = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModel = vp.get(getVMClass()!!);
        viewModel?.setLifecycleOwner(this);
        lifecycle.addObserver(viewModel!!);
        dataBinding?.lifecycleOwner = this;
        viewModel?.initModel();
        initView();
    }

    abstract fun getLayoutRes() : Int;

    abstract fun getVMClass() : Class<V>;

    abstract fun initView();

}