package com.god.uikit.widget

import android.content.Context
import android.os.Handler
import android.view.Gravity
import android.widget.Toast

class ViewToast {

    companion object{
        val LENGTH_SHORT = Toast.LENGTH_SHORT
        val LENGTH_LONG = Toast.LENGTH_LONG

        private var toast: Toast? = null
        private val handler = Handler()

        private val run = Runnable { toast!!.cancel() }


        private fun toast(
            ctx: Context,
            msg: CharSequence,
            duration: Int
        ) {
            var duration = duration
            handler.removeCallbacks(run)
            when (duration) {
                LENGTH_SHORT -> duration = 1000
                LENGTH_LONG -> duration = 3000
                else -> {
                }
            }
            if (null != toast) {
                toast!!.setText(msg)
            } else {
                toast = Toast.makeText(ctx, msg, duration)
            }
            toast!!.setGravity(Gravity.BOTTOM, 0, 50)
            handler.postDelayed(run, duration.toLong())
            toast!!.show()
        }


        /**
         * 弹出Toast
         *
         * @param ctx
         * 弹出Toast的上下文
         * @param msg
         * 弹出Toast的内容
         * @param duration
         * 弹出Toast的持续时间
         */
        fun show(
            ctx: Context,
            msg: CharSequence,
            duration: Int
        ) {
            var duration = duration
            if (0 > duration) {
                duration = LENGTH_SHORT
            }
            toast(ctx, msg, duration)
        }

        /**
         * 弹出Toast
         *
         * @param ctx
         * 弹出Toast的上下文
         * @param resId
         * 弹出Toast的内容的资源ID
         * @param duration
         * 弹出Toast的持续时间
         */
        fun show(ctx: Context, resId: Int, duration: Int) {
            var duration = duration
            if (0 > duration) {
                duration = LENGTH_SHORT
            }
            toast(ctx, ctx.resources.getString(resId), duration)
        }
    }

}