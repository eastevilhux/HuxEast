package com.good.framework.commons

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract open class BaseFragment<D : ViewDataBinding,V : BaseViewModel> : Fragment() {
    var viewModel : V = TODO();
    var dataBinding : D;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,true);
        return dataBinding!!.root;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        var vp = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application));
        viewModel = vp.get(getVMClass()!!);
        viewModel.setLifecycleOwner(this);
        lifecycle.addObserver(viewModel);
        dataBinding.lifecycleOwner = this;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.initOnFragmentActivityCreate();
    }

    abstract fun getLayoutRes():Int;

    abstract fun getVMClass() : Class<V>;

}