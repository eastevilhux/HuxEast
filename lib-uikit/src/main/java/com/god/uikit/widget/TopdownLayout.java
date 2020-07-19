package com.god.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutUpdownBinding;
import com.god.uikit.utils.ViewUtil;

public class TopdownLayout extends FrameLayout {

    private Drawable image;

    private int imageSize;

    private ObservableField<String> text;

    private int itemTextColor;

    private LayoutUpdownBinding dataBinding;

    public TopdownLayout(@NonNull Context context) {
        super(context);
    }

    public TopdownLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopdownLayout);
        Drawable tempBtp = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.icon_default_arrow));
        Drawable bd = ta.getDrawable(R.styleable.TopdownLayout_iconSrc);
        if(bd == null){
            image = tempBtp;
        }else{
            image = bd;
        }
        imageSize = ta.getInteger(R.styleable.TopdownLayout_imageSize,60);
        imageSize = ViewUtil.Companion.dip2px(context,imageSize);

        text = new ObservableField<>(ta.getString(R.styleable.TopdownLayout_itemText));

        itemTextColor = ta.getColor(R.styleable.TopdownLayout_itemTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));

        ta.recycle();
        init();
    }

    protected void init(){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.layout_updown,
                this,true);

        dataBinding.setText(text);
        dataBinding.textview.setTextColor(itemTextColor);
        dataBinding.imageview.setImageDrawable(image);
        ViewGroup.LayoutParams params = dataBinding.imageview.getLayoutParams();
        params.width = imageSize;
        params.height = imageSize;
        dataBinding.imageview.setLayoutParams(params);
    }
}
