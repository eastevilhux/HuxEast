package com.good.job.commons

class Error {

    var type:Type = Type.TYPE_TOAST_SHORT;
    var message:String? = null;
    var enter:String? = null;
    var cancel:String? = null;
    var tag = null;

    companion object{

        fun shortToast(message:String? = null) : Error{
            var error = Error();
            error.type = Type.TYPE_TOAST_SHORT;
            error.message = message;
            return error;
        }

        fun shortLong(message:String) : Error{
            var error = Error();
            error.type = Type.TYPE_TOAST_LONG;
            error.message = message;
            return error;
        }


        fun dialogMessage(message: String,enter:String) : Error{
            var error = Error();
            error.type = Type.TYPE_DIALOG_MSG;
            error.enter = enter;
            error.message = message;
            return error;
        }

        fun dialogInquiry(message:String,enter: String,cancel:String) : Error{
            var error = Error();
            error.type = Type.TYPE_DIALOG_INQUIRY;
            error.enter = enter;
            error.cancel = cancel;
            error.message = message;
            return error;
        }
    }
}