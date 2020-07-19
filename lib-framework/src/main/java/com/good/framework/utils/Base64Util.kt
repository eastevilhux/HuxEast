package com.good.framework.utils

import android.util.Base64
import com.good.framework.http.HttpConfig
import java.io.*

class Base64Util {
    companion object{
        /**文件缓冲区大小 */
        private const val CACHE_SIZE = 1024

        /**
         * BASE64字符串解码为二进制数据
         * @param base64    BASE64字符串
         * @return          解码后的二进制数据
         */
        fun decode(base64: String): ByteArray {
            return Base64.decode(base64.toByteArray(), Base64.DEFAULT)
        }


        /**
         * 二进制数据编码为BASE64字符串
         * @param bytes     二进制数据
         * @return          编码后的BASE64字符串
         */
        @Throws(UnsupportedEncodingException::class)
        fun encode(bytes: ByteArray?): String? {
            return String(Base64.encode(bytes, Base64.DEFAULT), HttpConfig.HTTP_CHARSET)
        }

        /**
         * 将文件编码为BASE64字符串
         * @param filePath      文件绝对路径
         * @return              BASE64字符串
         */
        @Throws(Exception::class)
        fun encodeFile(filePath: String?): String? {
            val bytes = fileToByte(filePath)
            return encode(bytes)
        }

        /**
         * BASE64字符串转回文件
         * @param filePath  文件绝对路径
         * @param base64    BASE64字符串
         */
        @Throws(Exception::class)
        fun decodeToFile(filePath: String?, base64: String) {
            val bytes = decode(base64)
            byteArrayToFile(bytes, filePath)
        }

        /**
         * 文件转换为二进制数组
         * @param filePath  文件绝对路径
         * @return          二进制数组
         */
        @Throws(Exception::class)
        fun fileToByte(filePath: String?): ByteArray {
            var data = ByteArray(0)
            val file = File(filePath)
            if (file.exists()) {
                val `in` = FileInputStream(file)
                val out = ByteArrayOutputStream(2048)
                val cache = ByteArray(CACHE_SIZE)
                var nRead: Int
                while (`in`.read(cache).also { nRead = it } != -1) {
                    out.write(cache, 0, nRead)
                    out.flush()
                }
                out.close()
                `in`.close()
                data = out.toByteArray()
            }
            return data
        }

        /**
         * 二进制数据写文件
         * @param bytes     二进制数据
         * @param filePath  文件绝对路径
         */
        @Throws(Exception::class)
        fun byteArrayToFile(bytes: ByteArray?, filePath: String?) {
            val `in`: InputStream = ByteArrayInputStream(bytes)
            val destFile = File(filePath)
            if (!destFile.parentFile.exists()) {
                destFile.parentFile.mkdirs()
            }
            destFile.createNewFile()
            val out: OutputStream = FileOutputStream(destFile)
            val cache = ByteArray(CACHE_SIZE)
            var nRead = 0
            while (`in`.read(cache).also { nRead = it } != -1) {
                out.write(cache, 0, nRead)
                out.flush()
            }
            out.close()
            `in`.close()
        }
    }
}