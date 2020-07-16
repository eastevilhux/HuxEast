package com.good.framework.utils;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

public class Test {

    public static void main(String[] args) {


    }


    public static SpannableStringBuilder changeText(int color,int start,int end,String str){
        SpannableStringBuilder s =  new SpannableStringBuilder(str);
        s.setSpan(new ForegroundColorSpan(color), start, end, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
        return s;
    }
}
