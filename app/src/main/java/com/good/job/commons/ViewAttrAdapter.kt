package com.good.job.commons

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.god.uikit.commons.GlideRoundTransform
import com.good.job.R

class ViewAttrAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("android:src")
        fun setImage(view: ImageView, icon: Int) {
            when (view.id) {
                else -> view.setImageResource(icon)
            }
        }

        @JvmStatic
        @BindingAdapter("android:src")
        fun setImage(view: ImageView, url: String?) {
            when (view.id) {
                R.id.user_imageview -> Glide.with(view).load(url)
                    .apply(
                        RequestOptions.bitmapTransform(GlideRoundTransform())
                            .placeholder(R.drawable.icon_user_head_default)
                            .error(R.drawable.icon_user_head_default)
                    )
                    .into(view)
                else -> {
                }
            }
        }
    }
}