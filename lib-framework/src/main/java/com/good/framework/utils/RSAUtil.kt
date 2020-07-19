package com.good.framework.utils

import com.good.framework.http.HttpConfig
import java.io.ByteArrayOutputStream
import java.security.*
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class RSAUtil {
    companion object{
        /**
         * 加密算法RSA
         */
        private const val KEY_ALGORITHM = "RSA"

        /**
         * 获取公钥的key
         */
        const val PUBLIC_KEY = "RSAPublicKey"

        /**
         * 获取私钥的key
         */
        const val PRIVATE_KEY = "RSAPrivateKey"

        /**
         * 签名算法
         */
        private const val SIGNATURE_ALGORITHM = "MD5withRSA"

        /**
         * 常量0
         */
        private const val ZERO = 0

        /**
         * RSA最大加密明文最大大小
         */
        private const val MAX_ENCRYPT_BLOCK = 117

        /**
         * RSA最大解密密文最大大小
         * 当密钥位数为1024时,解密密文最大是 128
         * 当密钥位数为2048时需要改为 256 不然会报错（Decryption error）
         */
        private const val MAX_DECRYPT_BLOCK = 128

        /**
         * 默认key大小
         */
        private const val DEFAULT_KEY_SIZE = 1024


        /** */
        /**  */
        /**
         *
         *
         * 生成密钥对(公钥和私钥)
         *
         *
         * @return
         * @throws NoSuchAlgorithmException
         * @throws Exception
         */
        @Throws(Exception::class)
        fun genKeyPair(): Map<String, String?> {
            val keyPairGen =
                KeyPairGenerator.getInstance(KEY_ALGORITHM)
            keyPairGen.initialize(1024)
            val keyPair = keyPairGen.generateKeyPair()
            val publicKey =
                keyPair.public as RSAPublicKey
            val privateKey =
                keyPair.private as RSAPrivateKey
            val keyMap: MutableMap<String, String?> =
                HashMap(2)
            keyMap[PUBLIC_KEY] = Base64Util.encode(publicKey.encoded)
            keyMap[PRIVATE_KEY] = Base64Util.encode(privateKey.encoded)
            return keyMap
        }

        /**
         * 公钥加密
         * @author hux
         * @createTime 2019年7月2日 2019年7月2日
         * @updateTime 2019年7月2日
         * @updateBy hux
         * @updateContext
         * version:1.0.0
         * 初始创建
         * @param data
         * 待加密的数据
         * @param publicKey
         * RSA公钥
         * @return
         * 加密后进行Base64编码的数据
         * @throws Exception
         */
        @Throws(Exception::class)
        fun encryptByPublicKey(
            data: String,
            publicKey: String?
        ): String? {
            val keyBytes = Base64Util.decode(publicKey!!)
            val x509KeySpec =
                X509EncodedKeySpec(keyBytes)
            val keyFactory =
                KeyFactory.getInstance(KEY_ALGORITHM)
            var entryByte = data.toByteArray(charset("UTF-8"))
            entryByte = encrypt(
                entryByte, KeyFactory.getInstance(KEY_ALGORITHM),
                keyFactory.generatePublic(x509KeySpec)
            )
            return Base64Util.encode(entryByte)
        }


        /**
         * 私钥加密
         *
         * @param data       源数据
         * @param privateKey 私钥(BASE64编码)
         * @return
         */
        fun encryptByPrivateKey(
            data: String,
            privateKey: String?
        ): String? {
            return try {
                val keyBytes = Base64Util.decode(privateKey!!)
                val pkcs8KeySpec =
                    PKCS8EncodedKeySpec(keyBytes)
                val keyFactory =
                    KeyFactory.getInstance(KEY_ALGORITHM)
                val privateK: Key = keyFactory.generatePrivate(pkcs8KeySpec)
                val entryByte = data.toByteArray(charset("UTF-8"))
                Base64Util.encode(encrypt(entryByte, keyFactory, privateK))
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }


        /**
         * 私钥解密
         * @author hux
         * @createTime 2019年7月2日 2019年7月2日
         * @updateTime 2019年7月2日
         * @updateBy hux
         * @updateContext
         * version:1.0.0
         * 初始创建
         * @param data
         * 需要解密的数据，该数据为RSA加密后进行Base64编码的字符串
         * @param privateKey
         * RSA私钥
         * @return
         * RSA私钥解密后的数据
         * @throws Exception
         */
        @Throws(Exception::class)
        fun decryptByPrivateKey(
            data: String?,
            privateKey: String?
        ): String {
            val keyBytes = Base64Util.decode(privateKey!!)
            val pkcs8KeySpec =
                PKCS8EncodedKeySpec(keyBytes)
            val keyFactory =
                KeyFactory.getInstance(KEY_ALGORITHM)
            val encryptedData = Base64Util.decode(data!!)
            return String(
                decrypt(
                    encryptedData, keyFactory,
                    keyFactory.generatePrivate(pkcs8KeySpec)
                )!!, HttpConfig.HTTP_CHARSET
            )
        }


        /**
         * 公钥解密
         *
         * @param data 已加密数据
         * @param publicKey     公钥(BASE64编码)
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        fun decryptByPublicKey(
            data: String?,
            publicKey: String?
        ): String? {
            val keyBytes = Base64Util.decode(publicKey!!)
            val x509KeySpec =
                X509EncodedKeySpec(keyBytes)
            val keyFactory =
                KeyFactory.getInstance(KEY_ALGORITHM)
            val publicK: Key = keyFactory.generatePublic(x509KeySpec)
            val encryptedData = Base64Util.decode(data!!)
            return String(decrypt(encryptedData, keyFactory, publicK)!!, HttpConfig.HTTP_CHARSET)
        }


        /**
         * 用私钥对信息生成数字签名
         *
         * @param data       已加密数据
         * @param privateKey 私钥(BASE64编码)
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        fun sign(data: ByteArray?, privateKey: String?): String? {
            val keyBytes = Base64Util.decode(privateKey!!)
            val pkcs8KeySpec =
                PKCS8EncodedKeySpec(keyBytes)
            val keyFactory =
                KeyFactory.getInstance(KEY_ALGORITHM)
            val privateK = keyFactory.generatePrivate(pkcs8KeySpec)
            val signature =
                Signature.getInstance(SIGNATURE_ALGORITHM)
            signature.initSign(privateK)
            signature.update(data)
            return Base64Util.encode(signature.sign())
        }


        /**
         * 校验数字签名
         *
         * @param data      已加密数据
         * @param publicKey 公钥(BASE64编码)
         * @param sign      数字签名
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        fun verify(
            data: ByteArray?,
            publicKey: String?,
            sign: String?
        ): Boolean {
            val keyBytes = Base64Util.decode(publicKey!!)
            val keySpec =
                X509EncodedKeySpec(keyBytes)
            val keyFactory =
                KeyFactory.getInstance(KEY_ALGORITHM)
            val publicK = keyFactory.generatePublic(keySpec)
            val signature =
                Signature.getInstance(SIGNATURE_ALGORITHM)
            signature.initVerify(publicK)
            signature.update(data)
            return signature.verify(Base64Util.decode(sign!!))
        }


        /**
         * 获取私钥
         *
         * @param keyMap 密钥对
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        fun getPrivateKey(keyMap: Map<String?, Any?>): String? {
            val key = keyMap[PRIVATE_KEY] as Key?
            return Base64Util.encode(key!!.encoded)
        }


        /**
         * 获取公钥
         *
         * @param keyMap 密钥对
         * @return
         * @throws Exception
         */
        @Throws(Exception::class)
        fun getPublicKey(keyMap: Map<String?, Any?>): String? {
            val key = keyMap[PUBLIC_KEY] as Key?
            return Base64Util.encode(key!!.encoded)
        }

        /**
         * 解密公共方法
         */
        @Throws(Exception::class)
        private fun decrypt(
            data: ByteArray,
            keyFactory: KeyFactory,
            key: Key
        ): ByteArray? {
            val cipher =
                Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.DECRYPT_MODE, key)
            return encryptAndDecrypt(data, cipher, MAX_DECRYPT_BLOCK)
        }

        /**
         * 加密公共方法
         */
        @Throws(Exception::class)
        private fun encrypt(
            data: ByteArray,
            keyFactory: KeyFactory,
            key: Key
        ): ByteArray {
            // 对数据加密
            val cipher =
                Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            return encryptAndDecrypt(data, cipher, MAX_ENCRYPT_BLOCK)
        }


        /**
         * 加密解密分段处理公共方法
         */
        @Throws(Exception::class)
        private fun encryptAndDecrypt(
            data: ByteArray,
            cipher: Cipher,
            maxSize: Int
        ): ByteArray {
            val inputLen = data.size
            val out = ByteArrayOutputStream()
            var offSet = ZERO
            var cache: ByteArray
            var i = ZERO
            // 对数据分段加密
            while (inputLen - offSet > ZERO) {
                cache = if (inputLen - offSet > maxSize) {
                    cipher.doFinal(data, offSet, maxSize)
                } else {
                    cipher.doFinal(data, offSet, inputLen - offSet)
                }
                out.write(cache, ZERO, cache.size)
                i++
                offSet = i * maxSize
            }
            val encryptedData = out.toByteArray()
            out.close()
            return encryptedData
        }


        @JvmStatic
        fun main(args: Array<String>) {
            try {
                //生成公钥与私钥
                val initKeyMap = genKeyPair()
                //公钥
                val publicKey = initKeyMap[PUBLIC_KEY]
                //私钥
                val privateKey = initKeyMap[PRIVATE_KEY]
                val str =
                    StringBuilder("WHAT A FUCK DAY!!! 不以粉嫩惊天下，但以风骚动世人")
                val bytes1 = encryptByPublicKey(str.toString(), privateKey)
                val bytes2 = decryptByPrivateKey(bytes1, publicKey)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}