package com.good.job.commons

import java.lang.StringBuilder

class Loading{
    var type:Type = Type.TYPE_TOAST_SHORT;
    var message:String? = null;
    var enter:String? = null;
    var cancel:String? = null;
    var tag = null;


    companion object{

        fun showDialog(message:String? = null) : Loading{
            var loading = Loading();
            loading.type = Type.TYPE_TOAST_SHORT;
            loading.message = message;
            return loading;
        }

        fun shortView(message:String) : Loading{
            var loading = Loading();
            loading.type = Type.TYPE_TOAST_LONG;
            loading.message = message;
            return loading;
        }


        fun dialogMessage(message: String,enter:String) : Loading{
            var loading = Loading();
            loading.type = Type.TYPE_DIALOG_MSG;
            loading.enter = enter;
            loading.message = message;
            return loading;
        }

        fun dialogInquiry(message:String,enter: String,cancel:String) : Loading{
            var loading = Loading();
            loading.type = Type.TYPE_DIALOG_INQUIRY;
            loading.enter = enter;
            loading.cancel = cancel;
            loading.message = message;
            return loading;
        }
    }
}