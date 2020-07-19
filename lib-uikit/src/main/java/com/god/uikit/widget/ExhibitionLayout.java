package com.god.uikit.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutExhibitionBinding;

public class ExhibitionLayout extends FrameLayout {

    private LayoutExhibitionBinding dataBinding;

    private ObservableField<Boolean> haveImage;

    private ObservableField<String> contentText;

    private ObservableField<Boolean> haveItem;
    private ObservableField<String> itemText;

    private ObservableField<Boolean> haveMenu;
    private ObservableField<String> menuText;

    private ObservableField<Boolean> haveItemImage;
    private ObservableField<Boolean> haveArrow;

    private Drawable image;
    private Drawable itemImage;
    private Drawable arrowImage;

    public ExhibitionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ExhibitionLayout);

        haveImage = new ObservableField<>(ta.getBoolean(R.styleable.ExhibitionLayout_haveImage,false));
        contentText = new ObservableField<>(ta.getString(R.styleable.ExhibitionLayout_contentText));
        haveItem = new ObservableField<>(ta.getBoolean(R.styleable.ExhibitionLayout_haveItem,false));
        itemText = new ObservableField<>(ta.getString(R.styleable.ExhibitionLayout_itemText));
        haveMenu = new ObservableField<>(ta.getBoolean(R.styleable.ExhibitionLayout_haveMenu,false));
        menuText = new ObservableField<>(ta.getString(R.styleable.ExhibitionLayout_menuText));
        haveItemImage = new ObservableField<>(ta.getBoolean(R.styleable.ExhibitionLayout_haveItemImage,false));
        haveArrow = new ObservableField<>(ta.getBoolean(R.styleable.ExhibitionLayout_haveArrow,false));

        Drawable tempBtp = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(), R.drawable.icon_onimg_default));

        Drawable bd = ta.getDrawable(R.styleable.ExhibitionLayout_exhibition_image);
        if(bd == null){
            image = tempBtp;
        }else{
            image = bd;
        }

        bd = ta.getDrawable(R.styleable.ExhibitionLayout_item_image);
        if(bd == null){
            bd = tempBtp;
        }else{
            itemImage = bd;
        }

        tempBtp = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(), R.drawable.icon_default_arrow));
        bd = ta.getDrawable(R.styleable.ExhibitionLayout_arrow_image);
        if(bd == null){
            arrowImage = tempBtp;
        }else{
            arrowImage = bd;
        }

        ta.recycle();

        initView();
    }

    private void initView() {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.layout_exhibition,this,true);
        dataBinding.setHaveImage(haveImage);
        dataBinding.setContent(contentText);
        dataBinding.setHaveItem(haveItem);
        dataBinding.setItemText(itemText);
        dataBinding.setHaveMenu(haveMenu);
        dataBinding.setMenuText(menuText);
        dataBinding.setHaveItemImage(haveItemImage);
        dataBinding.setHaveArrow(haveArrow);

        if(haveImage.get()){
            dataBinding.exhibitImage.setImageDrawable(image);
        }
        if(haveItemImage.get()){
            dataBinding.exhibitItemImage.setImageDrawable(itemImage);
        }
        if(haveArrow.get()){
            dataBinding.exhibitArrowImage.setImageDrawable(arrowImage);
        }
    }
}
