package com.good.framework.utils

import com.good.framework.http.HttpConfig
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

class AESUtil {
    companion object{
        /**
         * AES加密
         */
        const val ENTRY_TYPE_AES = "AES"


        /**
         * 使用指定的字符串生成秘钥
         */
        fun getKeyByPass(psdkey: String, keysize: Int): String? {
            //生成秘钥
            try {
                val kg = KeyGenerator.getInstance("AES")
                // kg.init(128);//要生成多少位，只�?��修改这里即可128, 192�?56
                //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就�?��，所以生成的秘钥就一样�?
                kg.init(keysize, SecureRandom(psdkey.toByteArray()))
                val sk = kg.generateKey()
                val b = sk.encoded
                return byteToHexString(b)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return null
        }


        /**
         * byte数组转化�?6进制字符�?
         * @param bytes
         * @return
         */
        fun byteToHexString(bytes: ByteArray): String {
            val sb = StringBuffer()
            for (i in bytes.indices) {
                val strHex = Integer.toHexString(bytes[i].toInt())
                if (strHex.length > 3) {
                    sb.append(strHex.substring(6))
                } else {
                    if (strHex.length < 2) {
                        sb.append("0$strHex")
                    } else {
                        sb.append(strHex)
                    }
                }
            }
            return sb.toString()
        }

        /**
         * AES加密
         * @author hux
         * @createTime 2015�?�?�?下午6:45:46
         * @createBy hux
         * @updateTime 2015�?�?�?
         * @updateBy hux
         * @updateContext
         * 1:初始创建
         * @param data
         * �?��加密的字符串
         * @param rawKey
         * 加密使用的密�?
         * @return
         * 加密后的字符�?
         */
        fun aesEncrypt(data: String, rawKey: String): String? {
            val key = rawKey.toByteArray()
            // Instantiate the cipher
            return try {
                val skeySpec =
                    SecretKeySpec(key, ENTRY_TYPE_AES)
                val cipher =
                    Cipher.getInstance(ENTRY_TYPE_AES)
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
                val encrypted = cipher.doFinal(data.toByteArray(charset("utf-8")))
                encryptBASE64(encrypted)
            } catch (e: Exception) {
                e.printStackTrace()
                // App.log.info("AES", "获取加密串出�?" + e.getMessage());
                ""
            }
        }

        /**
         * AES解密
         * @author hux
         * @createTime 2015�?�?�?下午6:46:36
         * @createBy hux
         * @updateTime 2015�?�?�?
         * @updateBy hux
         * @updateContext
         * 1:初始创建
         * @param encrypted
         * �?��解密的字符串
         * @param rawKey
         * 解密使用的密�?
         * @return
         * 解密后的字符�?
         */
        fun aesDecrypt(encrypted: String?, rawKey: String): String? {
            return try {
                val tmp = decryptBASE64(encrypted)
                val key = rawKey.toByteArray()
                val skeySpec =
                    SecretKeySpec(key, ENTRY_TYPE_AES)
                val cipher =
                    Cipher.getInstance(ENTRY_TYPE_AES)
                cipher.init(Cipher.DECRYPT_MODE, skeySpec)
                val decrypted = cipher.doFinal(tmp)
                String(decrypted, HttpConfig.HTTP_CHARSET)
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }


        /**
         * BASE64解密
         * @author hux
         * @createBy hux
         * @updateBy hux
         * @updateContext
         * 1:初始创建
         * @param key
         * 密钥
         * @return
         * 解密后的byte数组
         * @throws Exception
         */
        @Throws(Exception::class)
        fun decryptBASE64(key: String?): ByteArray? {
            return key?.let { Base64Util.decode(it) }
        }

        /**
         * BASE64加密
         * @author hux
         * @createBy hux
         * @updateBy hux
         * @updateContext
         * 1:初始创建
         * @param key
         * 密钥
         * @return
         * 加密后的字符�?
         * @throws Exception
         */
        @Throws(Exception::class)
        fun encryptBASE64(key: ByteArray?): String? {
            return Base64Util.encode(key)
        }


    }
}