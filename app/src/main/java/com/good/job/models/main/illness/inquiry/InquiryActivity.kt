package com.good.job.models.main.illness.inquiry

import android.view.View
import android.widget.RadioGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.good.job.R
import com.good.job.commons.AppActivity
import com.good.job.commons.Constants
import com.good.job.databinding.ActivityInquiryBinding

@Route(path = Constants.INQUIRY)
class InquiryActivity : AppActivity<ActivityInquiryBinding, InquiryViewModel>(),
    RadioGroup.OnCheckedChangeListener {

    override fun getLayoutRes(): Int  = R.layout.activity_inquiry;

    override fun getVMClass(): Class<InquiryViewModel>  = InquiryViewModel::class.java;

    override fun initView() {
        dataBinding?.titleLayout!!.onTitleListener = this;
        dataBinding?.viewModel = viewModel;
        dataBinding?.rgSex!!.setOnCheckedChangeListener(this)
        dataBinding?.inquiryac = this;
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p1){
            R.id.rb_boy->viewModel?.changeSex(InquiryViewModel.SEX_BOY);
            R.id.rb_girl->viewModel?.changeSex(InquiryViewModel.SEX_GIRL);
        }
    }

    fun onViewClick(view : View){
        when(view?.id){
            R.id.btn_havegenecinfo->viewModel?.chooseGenecinfo();
        }
    }

}