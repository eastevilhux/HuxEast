package com.good.job.commons

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.god.uikit.utils.StatusBarUtil
import com.god.uikit.widget.LoadingDialog
import com.god.uikit.widget.TitleLayout
import com.god.uikit.widget.ViewToast
import com.good.framework.commons.BaseActivity
import com.good.framework.http.HttpConfig
import com.good.framework.http.entity.Error
import com.good.job.R
import kotlinx.android.synthetic.main.activity_login.*

abstract class AppActivity<D : ViewDataBinding, V : EastViewModel<*>> : BaseActivity<D, V>(),
    TitleLayout.OnTitleListener {

    companion object{
        const val DEFAULT_CODE = 66;
    }

    private var loadingDialog : LoadingDialog? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(isImmersion()){
            //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
            StatusBarUtil.setRootViewFitsSystemWindows(this, false)
            //设置状态栏透明
            StatusBarUtil.setTranslucentStatus(this)
            //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
            //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
            //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
            //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
            if (!StatusBarUtil.setStatusBarDarkTheme(this, false)) {
                //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                //这样半透明+白=灰, 状态栏的文字能看得清
                StatusBarUtil.setStatusBarColor(this, 0x55000000)
            }
        }

        viewModel?.error?.observe(this, Observer {
            when(it.code){
                HttpConfig.CODE_NETWORK-> showToastShort(R.string.error_network)
                HttpConfig.CODE_SERVICE_ERROR -> showToastShort(R.string.error_service)
                -1 -> showToastShort(R.string.error_system)
                else->requestResult(it);
            }
        })

        viewModel?.loading?.observe(this, Observer {
            loadingDialog?:let {
                loadingDialog = LoadingDialog(this);
            }
            if(it){
                if(!loadingDialog!!.isShowing){
                    loadingDialog!!.show();
                }
            }else{
                if(loadingDialog!!.isShowing){
                    loadingDialog!!.dismiss();
                }
            }

        })
    }


    fun showToastShort(@StringRes strRes:Int){
        ViewToast.show(this,strRes,Toast.LENGTH_SHORT);
    }

    fun showToastShort(strRes:String){
        ViewToast.show(this,strRes,Toast.LENGTH_SHORT);
    }

    fun showToastLong(@StringRes strRes:Int){
        ViewToast.show(this,strRes,Toast.LENGTH_LONG);
    }

    fun showToastLong( strRes:String){
        ViewToast.show(this,strRes,Toast.LENGTH_LONG);
    }

    open fun requestResult(error:Error){
        if(error.code != Error.ERROR_DEFAULT){
            requestError(error);
        }
    }

    open fun requestError(error: Error){

    }


    open fun isImmersion() : Boolean{
        return false;
    }

    override fun onBack() {

    }

    override fun onMenu() {

    }

}