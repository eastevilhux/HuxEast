package com.good.framework.http.commons

class Constants {
    companion object{
        /**
         * 使用RSA方式进行解密标识
         */
        const val DECRYPT_RSA = 1

        /**
         * 使用AES方式解密标识
         */
        const val DECRYPT_AES = 2

        /**
         * 使用RSA加密数据标识
         */
        const val ENCRYPT_RSA = 3

        /**
         * 使用AES进行数据加密处理标识
         */
        const val ENCRYPT_AES = 4

        /**
         * 服务端RSA标识
         */
        const val SERVICE_TYPE_RSA = 1

        /**
         * 服务端AES标识
         */
        const val SERVICE_TYPE_AES = 2


        /**
         * 解密数据类型标识错误
         */
        const val CODE_DECRYPE_ERROR = -2

        /**
         * 在SharedPreferences缓存登录用户的appkey的key标识
         */
        const val SP_KEY_APPKEY = "appkey"

        /**
         * 接口请求key标识
         */
        var appKey: String? = null

        /**
         * 用户标识
         */
        val userid: String? = null

        /**
         * 前后端数据加密处理交互类型，1:RSA处理类型，2:AES处理类型
         */
        var serviceType = 0

        /**
         * 加密方式
         */
        var encrypType = 0

        /**
         * 解密方式
         */
        var decrypType = 0

        /**
         * 缓存的用来解析后端的key
         */
        var serviceKey: String? = null

        /**
         * 设置当前系统与后端交互方式为RSA，并设置相关类型
         *
         * create by Administrator at 2020/3/28 4:44
         * @author Administrator
         * @since 1.0.0
         * @return
         * void
         */
        fun setRSAType() {
            decrypType =
                DECRYPT_RSA;
            encrypType =
                ENCRYPT_RSA;
            serviceType =
                SERVICE_TYPE_RSA;
        }

        /**
         * 设置当前系统与后端交互方式为AES，并设置相关类型
         *
         * create by Administrator at 2020/3/28 4:44
         * @author Administrator
         * @since 1.0.0
         * @return
         * void
         */
        fun setAESType() {
            decrypType =
                DECRYPT_AES;
            encrypType =
                ENCRYPT_AES;
            serviceType =
                SERVICE_TYPE_AES;
        }
    }

}