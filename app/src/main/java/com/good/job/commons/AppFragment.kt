package com.good.job.commons

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.good.framework.commons.BaseFragment
import com.good.framework.http.HttpConfig
import com.good.framework.http.entity.Error
import com.good.job.R

abstract class AppFragment<D : ViewDataBinding, V : EastViewModel<*>> : BaseFragment<D,V>(){

    var activity : AppActivity<*,*>? = null;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as AppActivity<*, *>;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel?.error?.observe(this, Observer {
            when(it.code){
                HttpConfig.CODE_NETWORK->activity?.showToastLong(R.string.error_network)
                HttpConfig.CODE_SERVICE_ERROR -> activity?.showToastLong(R.string.error_service)
                -1->activity?.showToastShort(R.string.error_system)
                else->requestError(it);
            }
        })
    }

    open fun requestError(error: Error){

    }
}