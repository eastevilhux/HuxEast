package com.god.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutSelectitemBinding;


public class SelectitemLayout extends FrameLayout {

    private LayoutSelectitemBinding dataBinding;

    private Drawable itemImg;
    private Drawable arrowImg;

    private ObservableField<Boolean> haveItem;
    private ObservableField<Boolean> haveArrow;
    private ObservableField<Boolean> haveContent;
    private ObservableField<Boolean> haveItemImage;
    private ObservableField<String> itemText;
    private ObservableField<String> contentText;

    private ObservableField<Integer> contentGravity;

    private int itemTextColor,contentTextColor;

    public SelectitemLayout(@NonNull Context context) {
        super(context);
    }


    public SelectitemLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectitemLayout);

        Drawable tempBtp = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.icon_default_arrow));
        Drawable bd = ta.getDrawable(R.styleable.SelectitemLayout_arrow_image);
        if(bd == null){
            arrowImg = tempBtp;
        }else{
            arrowImg = bd;
        }
        tempBtp = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.icon_onimg_default));
        bd = ta.getDrawable(R.styleable.SelectitemLayout_item_image);
        if(bd == null){
            itemImg = tempBtp;
        }else{
            itemImg = bd;
        }
        haveItem = new ObservableField<>(ta.getBoolean(R.styleable.SelectitemLayout_haveItem,false));
        haveItemImage = new ObservableField<>(ta.getBoolean(R.styleable.SelectitemLayout_haveItemImage,false));
        haveArrow = new ObservableField<>(ta.getBoolean(R.styleable.SelectitemLayout_haveArrow,true));
        haveContent = new ObservableField<>(ta.getBoolean(R.styleable.SelectitemLayout_haveContent,true));
        itemText = new ObservableField<>(ta.getString(R.styleable.SelectitemLayout_itemText));
        contentText = new ObservableField<>(ta.getString(R.styleable.SelectitemLayout_contentText));

        itemTextColor = ta.getColor(R.styleable.SelectitemLayout_itemTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));

        contentTextColor = ta.getColor(R.styleable.SelectitemLayout_contentTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));


        int gravity = ta.getInteger(R.styleable.SelectitemLayout_content_gravity,1);
        switch (gravity){
            case 1:
                contentGravity = new ObservableField<>(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                break;
            case 2:
                contentGravity = new ObservableField<>(Gravity.CENTER);
                break;
            case 3:
                contentGravity = new ObservableField<>(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                break;
            default:
                contentGravity = new ObservableField<>(Gravity.CENTER);
                break;
        }
        ta.recycle();
        init();
    }

    protected void init(){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.layout_selectitem,
                this,true);
        dataBinding.setContentText(contentText);
        dataBinding.setHaveArrow(haveArrow);
        dataBinding.setHaveContent(haveContent);
        dataBinding.setItemText(itemText);
        dataBinding.setHaveItem(haveItem);
        dataBinding.setHaveItemImage(haveItemImage);
        dataBinding.setContentGravity(contentGravity);
        dataBinding.setItemTextColor(itemTextColor);
        dataBinding.setContentTextColor(contentTextColor);

        if(haveItemImage.get()){
            dataBinding.itemimageView.setImageDrawable(itemImg);
        }
        if(haveArrow.get()){
            dataBinding.arrowImageview.setImageDrawable(arrowImg);
        }
    }


    public void setContentText(String contentText) {
        this.contentText.set(contentText);
    }

    public String getContentText(){
        return contentText.get();
    }

    public void setItemText(String itemText) {
        this.itemText.set(itemText);
    }

    public String getItemText(){
        return itemText.get();
    }
}
