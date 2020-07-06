package com.good.framework.commons

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract open class BaseFragment<D : ViewDataBinding,V : BaseViewModel> : Fragment() {
    val TAG = "BaseFragment=>";
    lateinit var viewModel : V;
    lateinit var dataBinding : D;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false);
        return dataBinding!!.root;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG,"onActivityCreated");

        var vp = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application));
        viewModel = vp.get(getVMClass()!!);
        viewModel.setLifecycleOwner(this);
        lifecycle.addObserver(viewModel);
        dataBinding.lifecycleOwner = this;

        viewModel.initOnFragmentActivityCreate();

        initView()
    }

    abstract fun getLayoutRes():Int;

    abstract fun getVMClass() : Class<V>;

    open fun initView(){

    }

}