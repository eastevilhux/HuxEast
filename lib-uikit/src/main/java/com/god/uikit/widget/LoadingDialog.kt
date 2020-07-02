package com.god.uikit.widget

import android.app.Dialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import com.god.uikit.R
import com.god.uikit.databinding.DialogLoadingBinding
import com.god.uikit.utils.ViewUtil

class LoadingDialog(context: Context) : Dialog(context) {
    lateinit var dataBinding : DialogLoadingBinding;

    init {
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_loading,null,false);
        setContentView(dataBinding.root);

        //点击提示框外面是否取消提示框
        setCanceledOnTouchOutside(false)
        //点击返回键是否取消提示框
        setCancelable(false)

        val dialogWindow = window
        val lp = dialogWindow!!.attributes

        lp.width = ViewUtil.dip2px(context, 80)
        lp.height = ViewUtil.dip2px(context, 80)
        //lp.alpha = 0.7f; // 透明度
        //lp.alpha = 0.7f; // 透明度
        dialogWindow!!.attributes = lp
    }

    override fun show() {
        super.show()
    }

    override fun dismiss() {
        super.dismiss()
    }
}