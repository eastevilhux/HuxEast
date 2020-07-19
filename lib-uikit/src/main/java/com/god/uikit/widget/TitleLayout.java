package com.god.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutTitleBinding;


/**
 * Created by admin on 2018/7/31.
 */

public class TitleLayout extends FrameLayout {

    private LayoutTitleBinding dataBinding;

    private Bitmap backIcon;

    private Bitmap imageIcon;

    private ObservableField<Integer> titleColor;

    private ObservableField<Integer> menuColor;

    private ObservableField<String> title;

    private ObservableField<String> menu;

    private OnTitleListener onTitleListener;

    private ObservableField<Integer> iconBackType;

    private ObservableField<Integer> menuType;

    private ObservableField<String> backText;

    private int backTextColor;

    public TitleLayout(@NonNull Context context) {
        super(context);
    }

    public TitleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout);

        titleColor =  new ObservableField<>(ta.getColor(R.styleable.TitleLayout_titleTextColor,
                getResources().getColor(R.color.colorWhite)));

        menuColor = new ObservableField<>(ta.getColor(R.styleable.TitleLayout_menuTextColor,
                getResources().getColor(R.color.colorWhite)));

        backTextColor = ta.getColor(R.styleable.TitleLayout_backTextColor,getResources().getColor(R.color.colorBlack));

        BitmapDrawable bd = (BitmapDrawable) ta.getDrawable(R.styleable.TitleLayout_iconBack);
        if (bd != null) {
            backIcon  = bd.getBitmap();
        } else {
            backIcon = BitmapFactory.decodeResource(getResources(), R.drawable.back_arrow);
        }

        bd = (BitmapDrawable) ta.getDrawable(R.styleable.TitleLayout_menuImage);
        if(bd != null){
            imageIcon = bd.getBitmap();
        }else{
            imageIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon_onimg_default);
        }
        title = new ObservableField<>(ta.getString(R.styleable.TitleLayout_titleText));
        menu = new ObservableField<>(ta.getString(R.styleable.TitleLayout_menuText));

        menuType = new ObservableField<>(ta.getInt(R.styleable.TitleLayout_menuType,0));

        backText = new ObservableField<>(ta.getString(R.styleable.TitleLayout_backText));

        iconBackType = new ObservableField<>(ta.getInt(R.styleable.TitleLayout_iconBackType,1));

        ta.recycle();
        init();
    }

    private void init(){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_title,
                this,true);
        dataBinding.setIconBackType(iconBackType);
        switch (iconBackType.get()){
            case 1:
                //显示正方形图标
                dataBinding.iconBackSquare.setImageBitmap(backIcon);
                break;
            case 2:
                //显示长方形图标
                dataBinding.iconBackRectangle.setImageBitmap(backIcon);
                break;
            default:
                break;
        }
        dataBinding.setMenuColor(menuColor);
        dataBinding.setTitleColor(titleColor);
        dataBinding.setTitle(title);
        dataBinding.setMenuStr(menu);
        dataBinding.setMenuType(menuType);
        dataBinding.setTitleLayout(this);
        dataBinding.setBackText(backText);
        dataBinding.backText.setTextColor(backTextColor);

        switch (menuType.get()){
            case 2:
                dataBinding.menuImage.setImageBitmap(imageIcon);
                break;
            default:
                break;
        }
    }


    public void setBackIcon(Bitmap backIcon) {
        this.backIcon = backIcon;
        switch (iconBackType.get()){
            case 1:
                //显示正方形图标
                dataBinding.iconBackSquare.setImageBitmap(backIcon);
                break;
            case 2:
                //显示长方形图标
                dataBinding.iconBackRectangle.setImageBitmap(backIcon);
                break;
            default:
                break;
        }
    }

    public void setBackText(String backText){
        this.backText.set(backText);
    }


    public Bitmap getBackIcon() {
        return backIcon;
    }

    public void setBackIcon(@DrawableRes int  iconRes) {
        backIcon =  BitmapFactory. decodeResource (getResources(),iconRes);
    }

    public void setTitle(String title){
        this.title.set(title);
    }

    public void setMenuType(int type){
        this.menuType.set(type);
    }


    public void setTitleText(String title){
        this.title.set(title);
    }

    public String getTitle(){
        return title.get();
    }

    /**
     * 返回按钮点击事件监听
     * @author hux
     * @createTime 2018/8/2 11:46
     * @since 0.0.1
     * @see LayoutTitleBinding
     * @param view
     *      {@link View}
     */
    public void back(View view){
        if(onTitleListener != null){
            onTitleListener.onBack();
        }
    }

    /**
     * 标题菜单按钮点击事件监听
     * @author admin
     * @createTime 2018/8/3 14:45
     * @since TitleLayout
     * @see
     * @params
     * @return
     */
    public void menu(View view){
        if(onTitleListener != null){
            onTitleListener.onMenu();
        }
    }

    public OnTitleListener getOnTitleListener() {
        return onTitleListener;
    }

    public void setOnTitleListener(OnTitleListener onTitleListener) {
        this.onTitleListener = onTitleListener;
    }

    public void setMenu(String menu) {
        this.menu.set(menu);
    }


    public void setIconBackType(int iconBackType) {
        this.iconBackType.set(iconBackType);
        switch (this.iconBackType.get()){
            case 1:
                //显示正方形图标
                dataBinding.iconBackSquare.setImageBitmap(backIcon);
                break;
            case 2:
                //显示长方形图标
                dataBinding.iconBackRectangle.setImageBitmap(backIcon);
                break;
            default:
                break;
        }
    }

    public interface OnTitleListener{
        void onBack();

        void onMenu();
    }

}
