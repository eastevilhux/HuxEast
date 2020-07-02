package com.good.framework.commons

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<D : ViewDataBinding,V : BaseViewModel> : AppCompatActivity(){
    lateinit var dataBinding : D;
    lateinit var viewModel:V;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,getLayoutRes())
        var vp = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        viewModel = vp.get(getVMClass()!!);
        viewModel.setLifecycleOwner(this);
        lifecycle.addObserver(viewModel);
        dataBinding.lifecycleOwner = this;
        initView();
        viewModel.initOnActivityCrate();
    }

    abstract fun getLayoutRes() : Int;

    abstract fun getVMClass() : Class<V>;

    abstract fun initView();

}