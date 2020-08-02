package com.god.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutNumbereditBinding;

public class EastEditText extends FrameLayout implements TextWatcher {

    private static final String TAG = "EastEditText==>";

    private LayoutNumbereditBinding dataBinding;

    private int maxNumber;

    private int textSize;

    private ObservableField<String> hintText;

    private int hintColor;

    private ObservableField<String> inputNumber;

    private OnEastEditTextListener onEastEditTextListener;

    private int minHeight;

    public EastEditText(@NonNull Context context) {
        super(context);
    }

    public EastEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
    }

    public EastEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context,attrs);
    }

    protected void initAttr(Context context,AttributeSet attrs){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EastEditText);
        maxNumber = ta.getInt(R.styleable.EastEditText_maxNumber,0);
        textSize = ta.getDimensionPixelSize(R.styleable.EastEditText_android_textSize, 13);
        minHeight = ta.getDimensionPixelSize(R.styleable.EastEditText_android_minHeight,0);
        hintColor = ta.getColor(R.styleable.EastEditText_hintTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));
        hintText = new ObservableField<>(ta.getString(R.styleable.EastEditText_hintText));
        ta.recycle();
        init();
    }


    protected void init(){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_numberedit,
                this,true);
        dataBinding.getRoot().setMinimumHeight(minHeight);
        inputNumber = new ObservableField<>("0");
        if(onEastEditTextListener != null){
            onEastEditTextListener.initEdit(dataBinding.editText);
        }
        dataBinding.editText.addTextChangedListener(this);
        dataBinding.editText.setHintTextColor(hintColor);
        dataBinding.setInputNumber(inputNumber);
        dataBinding.setMaxNumber(String.valueOf(maxNumber));
        dataBinding.setHintText(hintText);
        dataBinding.tvInputnumber.setTextSize(textSize);
        dataBinding.tvLine.setTextSize(textSize);
        dataBinding.tvMaxnumber.setTextSize(textSize);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(editable != null && editable.length() > 0) {
            inputNumber.set(String.valueOf(editable.length()));
            if(onEastEditTextListener != null){
                onEastEditTextListener.onInput(editable.toString());
            }
            if(editable.length() > maxNumber){
                if(onEastEditTextListener != null){
                    onEastEditTextListener.onOutofRange();
                }
            }
        }else {
            inputNumber.set("0");
        }
    }

    public void setOnEastEditTextListener(OnEastEditTextListener onEastEditTextListener) {
        this.onEastEditTextListener = onEastEditTextListener;
    }

    public interface OnEastEditTextListener{

        void onInput(String text);

        void onOutofRange();

        void initEdit(EditText edittext);
    }

}
