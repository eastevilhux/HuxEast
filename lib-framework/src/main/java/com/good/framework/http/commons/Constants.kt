package com.good.framework.http.commons

class Constants {
    companion object{

        /**
         * 数据加密类型
         */
        var encrypType : EncrypType = EncrypType.ENCRYPE_RSA;

        /**
         * 数据解密类型
         */
        var decrypType : DecrypType = DecrypType.DECRYPT_RSA;

        var encrypKey : String? = null;

        var appKey : String? = null;

        var userid : String ? = null;

        /**
         * 前后端数据加密处理交互类型，1:RSA处理类型，2:AES处理类型
         */
        var serviceType : ServiceType = ServiceType.SERVICE_TYPE_RSA;

        var serviceKey : String? = null;

        fun setAESType(){
            encrypType = EncrypType.ENCRYPE_AES;
            decrypType = DecrypType.DECRYPT_AES;
            serviceType = ServiceType.SERVICE_TYPE_AES;
        }

        fun setRSAType(){
            encrypType = EncrypType.ENCRYPE_RSA;
            decrypType = DecrypType.DECRYPT_RSA;
            serviceType = ServiceType.SERVICE_TYPE_RSA;
        }
    }


    enum class EncrypType(type:Int){
        ENCRYPE_AES(1),
        ENCRYPE_RSA(2);
    }

    enum class DecrypType(type:Int){
        DECRYPT_AES(3),
        DECRYPT_RSA(4);
    }

    enum class ServiceType(type:Int){
        SERVICE_TYPE_AES(2),
        SERVICE_TYPE_RSA(1);
    }
}