package com.god.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutInputBinding;

public class InputLayout extends FrameLayout implements CountDownTimerButton.OnCountdownListener {

    private ObservableField<String> itemText;

    private ObservableField<String> hintText;

    private ObservableField<Boolean> haveLine;

    private ObservableField<Boolean> haveMenu;

    private ObservableField<String> menuText;

    private int itemTextColor;

    private int editTextColor;

    private int hintColor;

    private int lineColor;

    private int menuColor;

    private Drawable menuBack;

    private int inputType;

    private LayoutInputBinding dataBinding;

    private OnInputMenuClickListener onInputMenuClickListener;

    private OnCountdownListener onCountdownListener;

    public InputLayout(@NonNull Context context) {
        super(context);
    }

    public InputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.InputLayout);
        itemText = new ObservableField<>(ta.getString(R.styleable.InputLayout_itemText));
        hintText = new ObservableField<>(ta.getString(R.styleable.InputLayout_hintText));
        haveLine = new ObservableField<>(ta.getBoolean(R.styleable.InputLayout_haveLine,false));
        haveMenu = new ObservableField<>(ta.getBoolean(R.styleable.InputLayout_haveMenu,false));
        if(haveMenu.get()){
            menuText = new ObservableField<>(ta.getString(R.styleable.InputLayout_itemMenuText));
        }

        itemTextColor = ta.getColor(R.styleable.InputLayout_itemTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));

        editTextColor = ta.getColor(R.styleable.InputLayout_inputTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));

        hintColor = ta.getColor(R.styleable.InputLayout_hintColor,
                ContextCompat.getColor(getContext(),R.color.colorHint));

        lineColor = ta.getColor(R.styleable.InputLayout_lineColor,
                ContextCompat.getColor(getContext(),R.color.colorLine));

        menuColor = ta.getColor(R.styleable.InputLayout_menuTextColor,
                ContextCompat.getColor(getContext(),R.color.colorWhite));

        menuBack = ta.getDrawable(R.styleable.InputLayout_menuBackground);
        inputType = ta.getInt(R.styleable.InputLayout_inputType,0);

        ta.recycle();
        initView();
    }

    protected void initView(){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_input,
                this,true);
        dataBinding.setInputLayout(this);
        dataBinding.setItemText(itemText);
        dataBinding.setHintText(hintText);
        dataBinding.setHaveLine(haveLine);

        dataBinding.itemMenutext.setTextColor(itemTextColor);
        dataBinding.inputEditview.setTextColor(editTextColor);
        dataBinding.inputEditview.setHintTextColor(hintColor);
        dataBinding.lineText.setBackgroundColor(lineColor);
        dataBinding.setHaveMenu(haveMenu);
        if(menuBack != null){
            dataBinding.menuButton.setBackground(menuBack);
            dataBinding.menuButton.setTextColor(menuColor);
        }
        if(haveMenu.get()){
            dataBinding.setMenuText(menuText);
        }

        switch (inputType){
            case 0:
                break;
            case 1:
                dataBinding.inputEditview.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case 2:
                dataBinding.inputEditview.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 3:
                dataBinding.inputEditview.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                dataBinding.inputEditview.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case 4:
                dataBinding.inputEditview.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                dataBinding.inputEditview.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            default:
                break;
        }

        dataBinding.menuButton.setOnCountdownListener(this);
    }

    public void onMenuClick(View view){
        if(onInputMenuClickListener != null){
            onInputMenuClickListener.onMenuClick(this);
        }
    }

    public void startTime(long time){
        dataBinding.menuButton.start(time);
    }

    public String getInputText(){
        return dataBinding.inputEditview.getText().toString();
    }

    @Override
    public void onStart(View view) {
        if(onCountdownListener != null){
            onCountdownListener.onStart(dataBinding.menuButton);
        }
    }

    @Override
    public void onTime(long time, View view) {
        if(onCountdownListener != null){
            int t = (int) (time/1000);
            onCountdownListener.onTime(dataBinding.menuButton,t);
        }
    }

    @Override
    public void onStop(View view) {
        if(onCountdownListener != null){
            onCountdownListener.onStop(dataBinding.menuButton);
        }
    }


    public interface OnInputMenuClickListener{

        /**
         * 按钮点击事件监听
         * <p>create by Administrator at 2020/3/31 0:10
         * @author Administrator
         * @since 1.0.0
         * @param view
         *      触发事件的View
         * @return
         *      void
         */
        void onMenuClick(View view);
    }

    public interface OnCountdownListener{

        void onStart(TextView view);

        void onTime(TextView view, int time);

        void onStop(TextView view);
    }

    public void setOnInputMenuClickListener(OnInputMenuClickListener onInputMenuClickListener) {
        this.onInputMenuClickListener = onInputMenuClickListener;
    }

    public OnInputMenuClickListener getOnInputMenuClickListener() {
        return onInputMenuClickListener;
    }


    public void setOnCountdownListener(OnCountdownListener onCountdownListener) {
        this.onCountdownListener = onCountdownListener;
    }

    public OnCountdownListener getOnCountdownListener() {
        return onCountdownListener;
    }
}
