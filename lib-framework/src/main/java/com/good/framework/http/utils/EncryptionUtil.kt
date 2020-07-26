package com.good.framework.http.utils

import android.util.Log
import com.god.uikit.utils.isEmpty
import com.good.framework.http.commons.Constants
import com.good.framework.utils.AESUtil
import com.good.framework.utils.RSAUtil

class EncryptionUtil {

    companion object{

        /**
         * 加密数据
         */
        fun encryptionData(data : String) : String{
            if(data.isEmpty())
                return data;
            Constants.serviceKey?.let {
                when(Constants.encrypType){
                    Constants.EncrypType.ENCRYPE_AES ->{
                        //使用AES加密
                        return AESUtil.aesEncrypt(data,it) ?: data;
                    }
                    Constants.EncrypType.ENCRYPE_RSA ->{
                        //使用RSA加密
                        return RSAUtil.encryptByPublicKey(data,it) ?: data;
                    }
                    else -> return data;
                }
            }?: return data;
        }

    }
}