package com.god.uikit.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.god.uikit.R
import com.god.uikit.databinding.LayoutVerticalBinding

class VerticalImageText : FrameLayout {
    private var dataBinding: LayoutVerticalBinding? = null

    private val content = ObservableField<String>();
    private val imageResource = ObservableField<Int>();
    private val imageUrl = ObservableField<String>();

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attr: AttributeSet?) : super(
        context,
        attr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_vertical, this, true)

    }
}