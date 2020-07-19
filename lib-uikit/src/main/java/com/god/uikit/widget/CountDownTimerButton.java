package com.god.uikit.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

import androidx.annotation.Nullable;

import com.god.uikit.R;

@SuppressLint("AppCompatCustomView")
public class CountDownTimerButton extends TextView {

    /**
     * 开始倒计时code
     */
    private final int MSG_WHAT_START = 10_010;

    private final int MSG_WHAT_STOP = 10_020;

    private final int MSG_WHAT_DOING = 10_040;

    /**
     * 短信倒计时时间
     */
    private long mCountDownMillis = 60000;

    private OnCountdownListener onCountdownListener;


    /**
     * 可用状态下字体颜色Id
     */
    private int usableColor;
    /**
     * 不可用状态下字体颜色Id
     */
    private int unusableColor;

    private int status;

    private boolean unable;

    private CountdownHandler handler;

    public CountDownTimerButton(Context context) {
        super(context);
    }

    public CountDownTimerButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CountDownTimerButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountdownButton);

        //获取定义的倒计时时间
        mCountDownMillis = typedArray.getInt(R.styleable.CountdownButton_countDownMillis,6000);

        status = typedArray.getInteger(R.styleable.CountdownButton_status,1);
        unable = status == 1 ? true : false;

        usableColor = typedArray.getColor(R.styleable.CountdownButton_usableColor, Color.BLACK);
        unusableColor = typedArray.getColor(R.styleable.CountdownButton_unusableColor, Color.GRAY);

        typedArray.recycle();

        handler = new CountdownHandler(this);
    }

    public void setCountDownMillis(long mCountDownMillis) {
        this.mCountDownMillis = mCountDownMillis;
    }

    public void setUsable(boolean usable){
        if (usable) {
            //可用
            if (!isClickable()) {
                setClickable(usable);
                setTextColor(unusableColor);
            }
        } else {
            //不可用
            if (isClickable()) {
                setClickable(usable);
                setTextColor(unusableColor);
            }
        }
    }

    public void setUnusableColor(int unusableColor) {
        this.unusableColor = unusableColor;
    }

    public void setUsableColor(int usableColor) {
        this.usableColor = usableColor;
    }

    /**
     * 开始倒计时
     */
    public void start(long time) {
        mCountDownMillis = time;
        unable = true;
        handler.sendEmptyMessage(MSG_WHAT_START);
    }

    public void stop(){
        mCountDownMillis = 0;
        unable = false;
        handler.sendEmptyMessage(MSG_WHAT_STOP);
        setEnabled(true);
        setClickable(true);
        setFocusable(true);
    }

    public void setOnCountdownListener(OnCountdownListener onCountdownListener) {
        this.onCountdownListener = onCountdownListener;
    }

    public OnCountdownListener getOnCountdownListener() {
        return onCountdownListener;
    }

    private class CountdownHandler extends Handler {
        WeakReference<CountDownTimerButton> weakReference;

        public CountdownHandler(CountDownTimerButton countdownButton){
            weakReference = new WeakReference<CountDownTimerButton>(countdownButton);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if ( weakReference.get() != null ){
                switch (msg.what){
                    case MSG_WHAT_START:
                        //判断时间是否大于0，切当前是否为可用状态
                        if(unable && mCountDownMillis > 0){
                            handler.sendEmptyMessage(MSG_WHAT_DOING);
                            if(onCountdownListener != null)
                                onCountdownListener.onStart(CountDownTimerButton.this);
                        }
                        break;
                    case MSG_WHAT_STOP:
                        if(onCountdownListener != null)
                            onCountdownListener.onStop(CountDownTimerButton.this);
                        stop();
                        break;
                    case MSG_WHAT_DOING:
                        Log.d("countdown===>",mCountDownMillis+"秒");
                        if(unable && mCountDownMillis > 0){
                            setFocusable(false);
                            setEnabled(false);
                            setClickable(false);
                            mCountDownMillis = mCountDownMillis - 1000;
                            handler.sendEmptyMessageDelayed(MSG_WHAT_DOING,1000);
                            if(onCountdownListener != null)
                                onCountdownListener.onTime(mCountDownMillis,CountDownTimerButton.this);
                        }else{
                            handler.sendEmptyMessage(MSG_WHAT_STOP);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }


    public interface OnCountdownListener{

        /**
         * 短信倒计时开始事件监听
         * @author hux
         * @createTime 2018/3/30 13:15
         * @since 0.0.1
         * @param view
         *      触发事件的控件
         */
        public void onStart(View view);

        /**
         * 短信倒计时进行中事件监听
         * @author admin
         * @createTime 2018/3/30 13:16
         * @since 0.0.1
         * @param time
         *      当前时间
         * @param view
         *      触发事件的控件
         */
        public void onTime(long time, View view);

        /**
         * 短信倒计时结束事件监听
         * @author hux
         * @createTime 2018/3/30 13:15
         * @since 0.0.1
         * @param view
         *      触发事件的控件
         */
        public void onStop(View view);
    }
}
