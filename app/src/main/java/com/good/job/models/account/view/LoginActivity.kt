package com.good.job.models.account.view

import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.good.framework.entity.VMData
import com.good.framework.http.entity.Error
import com.good.job.R
import com.good.job.commons.AppActivity
import com.good.job.commons.Constants
import com.good.job.databinding.ActivityLoginBinding
import com.good.job.models.account.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = Constants.LOGIN)
class LoginActivity : AppActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun getLayoutRes(): Int  = R.layout.activity_login;

    override fun getVMClass(): Class<LoginViewModel> = LoginViewModel::class.java;

    override fun initView() {
        dataBinding?.titleLayout?.onTitleListener = this;
        dataBinding?.loginac = this;

        viewModel?.data?.observe(this, Observer {
            when(it.code){
                VMData.Code.CODE_SUCCESS->{
                    setResult(DEFAULT_CODE);
                    finish();
                }
            }
        })
    }

    fun login(view : View){
        var account = account_layout.inputText;
        if(account.isEmpty()){
            showToastShort(R.string.please_input_account)
            return;
        }
        var password = password_layout.inputText;
        if(password.isEmpty() || password.length < 6){
            showToastShort(R.string.please_input_loginpsd)
            return;
        }
        viewModel?.login(account,password,isLoading = true);
    }

    override fun onMenu() {
        super.onMenu()
    }

    override fun requestError(error: Error) {
        super.requestError(error)
        showToastShort(error.msg?:getString(R.string.error_system));
    }
}