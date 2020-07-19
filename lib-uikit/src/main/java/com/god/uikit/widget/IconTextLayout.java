package com.god.uikit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class IconTextLayout extends ViewGroup {
    private static final String TAG = "IconTextLayout==>";

    private int gravity = 2;

    public IconTextLayout(Context context) {
        super(context);
    }

    public IconTextLayout(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
    }

    public IconTextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }

    public IconTextLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context,attrs,defStyleAttr,defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            if (v.getVisibility() != View.GONE) {
                int childWidth = v.getMeasuredWidth();
                int childHeight = v.getMeasuredHeight();
                v.layout(l, t, l + childWidth, t + childHeight);
                t += childHeight;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = 0;
        int measuredHeight = 0;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            if (v.getVisibility() != View.GONE) {
                measureChild(v, widthMeasureSpec, heightMeasureSpec);
                measuredWidth=Math.max(measuredWidth,v.getMeasuredWidth());
                measuredHeight += v.getMeasuredHeight();
            }
        }
        measuredWidth += getPaddingLeft() + getPaddingRight();
        measuredHeight += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(measuredWidth, measuredHeight);
    }
}
