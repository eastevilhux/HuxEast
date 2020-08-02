package com.good.job.commons

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.god.uikit.utils.isEmpty
import com.good.framework.commons.BaseActivity
import java.io.Serializable

class BaseArouter private constructor(builder: Builder) {
    private val activity: BaseActivity<*, *>
    private val tag: String
    private val isFinish: Boolean
    private val intKey: String?
    private val intVlaue: Int
    private val stringValue: String?
    private val stringKey: String?
    private val serKey: String?
    private val serValue: Serializable?
    private val booleanKey: String?
    private val booleanValue: Boolean

    fun start() {
        val postcard = ARouter.getInstance()
            .build(tag)

        intKey?.let {
            if(!it.isEmpty()){
                postcard.withInt(intKey,intVlaue);
            }
        }

        booleanKey?.let {
            if(!it.isEmpty()){
                postcard.withBoolean(booleanKey,booleanValue);
            }
        }

        stringKey?.let{
            if(!it.isEmpty()){
                postcard.withString(stringKey,stringValue);
            }
        }

        serKey?.let {
            if(!it.isEmpty()){
                postcard.withSerializable(serKey,serValue);
            }
        }

        postcard.navigation(activity, object : NavCallback() {
            override fun onArrival(postcard: Postcard) {
                if (isFinish) {
                    activity.finish()
                }
            }
        })
    }

    class Builder(internal val tag: String, internal val activity: BaseActivity<*, *>) {
        internal var isFinish = false
        internal var intKey: String? = null
        internal var intVlaue = 0
        internal var stringValue: String? = null
        internal var stringKey: String? = null
        internal var serKey: String? = null
        internal var serValue: Serializable? = null
        internal var booleanKey: String? = null
        internal var booleanValue = false
        fun isFinish(isFinish: Boolean): Builder {
            this.isFinish = isFinish
            return this
        }

        fun addValue(
            intKey: String?,
            intVlaue: Int
        ): Builder {
            this.intKey = intKey
            this.intVlaue = intVlaue
            return this
        }

        fun addValue(
            booleanKey: String?,
            booleanValue: Boolean
        ): Builder {
            this.booleanKey = booleanKey
            this.booleanValue = booleanValue
            return this
        }

        fun addValue(
            stringKey: String?,
            stringValue: String?
        ): Builder {
            this.stringKey = stringKey
            this.stringValue = stringValue
            return this
        }

        fun addValue(
            serKey: String?,
            serValue: Serializable?
        ): Builder {
            this.serKey = serKey
            this.serValue = serValue
            return this
        }

        fun builder(): BaseArouter {
            return BaseArouter(this)
        }

    }

    init {
        tag = builder.tag
        isFinish = builder.isFinish
        intKey = builder.intKey
        intVlaue = builder.intVlaue
        booleanKey = builder.booleanKey
        booleanValue = builder.booleanValue
        stringKey = builder.stringKey
        stringValue = builder.stringValue
        serKey = builder.serKey
        serValue = builder.serValue
        activity = builder.activity
    }
}