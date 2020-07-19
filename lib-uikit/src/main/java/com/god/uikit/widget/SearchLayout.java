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
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.god.uikit.R;
import com.god.uikit.databinding.LayoutSearchBinding;

public class SearchLayout extends FrameLayout {

    private ObservableField<Boolean> haveMenu;
    private ObservableField<Boolean> haveMenuImage;
    private ObservableField<Boolean> haveMenuLine;
    private ObservableField<Boolean> haveArrow;
    private ObservableField<Boolean> haveCondition;
    private ObservableField<Boolean> haveConditionLine;
    private ObservableField<String> menuText;
    private ObservableField<String> hintSearchText;
    private ObservableField<String> conditionText;

    private Drawable menuDrawable;
    private Drawable arrowDrawable;

    private int menuTextColor;
    private int conditionTextColor;
    private int menuLineColor;
    private int conditionLineColor;
    private Drawable searchBg;

    private LayoutSearchBinding dataBinding;

    public SearchLayout(@NonNull Context context) {
        super(context);
    }

    public SearchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SearchLayout);

        haveMenu = new ObservableField<>(ta.getBoolean(R.styleable.SearchLayout_haveMenu,false));
        haveMenuImage = new ObservableField<>(ta.getBoolean(R.styleable.SearchLayout_haveMenuImage,false));
        haveMenuLine = new ObservableField<>(ta.getBoolean(R.styleable.SearchLayout_haveMenuLine,false));
        haveArrow = new ObservableField<>(ta.getBoolean(R.styleable.SearchLayout_haveArrow,false));
        haveCondition = new ObservableField<>(ta.getBoolean(R.styleable.SearchLayout_haveCondition,false));
        haveConditionLine = new ObservableField<>(ta.getBoolean(R.styleable.SearchLayout_haveConditionLine,false));
        menuText = new ObservableField<>(ta.getString(R.styleable.SearchLayout_menuText));
        hintSearchText = new ObservableField<>(ta.getString(R.styleable.SearchLayout_hintText));
        conditionText = new ObservableField<>(ta.getString(R.styleable.SearchLayout_conditionText));

        menuTextColor = ta.getColor(R.styleable.SearchLayout_menuTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));

        conditionTextColor = ta.getColor(R.styleable.SearchLayout_menuTextColor,
                ContextCompat.getColor(getContext(),R.color.colorApp));

        menuLineColor = ta.getColor(R.styleable.SearchLayout_menuLineColor,
                ContextCompat.getColor(getContext(),R.color.colorWhite));

        conditionLineColor = ta.getColor(R.styleable.SearchLayout_conditionLineColor,
                ContextCompat.getColor(getContext(),R.color.colorWhite));

        Drawable tempBtp = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.icon_onimg_default));
        Drawable bd = ta.getDrawable(R.styleable.SearchLayout_menuImage);
        if(bd == null){
            menuDrawable = tempBtp;
        }else{
            menuDrawable = bd;
        }

        bd = ta.getDrawable(R.styleable.SearchLayout_arrow_image);
        if(bd == null){
            arrowDrawable = tempBtp;
        }else{
            arrowDrawable = bd;
        }
        tempBtp = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.color.colorWhite));
        bd = ta.getDrawable(R.styleable.SearchLayout_searchBackgroundColor);
        if(bd == null){
            searchBg = tempBtp;
        }else{
            searchBg = bd;
        }
        ta.recycle();
        init();
    }

    protected void init() {
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_search,
                this, true);
        dataBinding.setHaveMenu(haveMenu);
        dataBinding.setHaveMenuImage(haveMenuImage);
        dataBinding.setHaveMenuLine(haveMenuLine);
        dataBinding.setHaveArrow(haveArrow);
        dataBinding.setHaveCondition(haveCondition);
        dataBinding.setHaveConditionLine(haveConditionLine);
        dataBinding.setMenuText(menuText);
        dataBinding.setHintSearchText(hintSearchText);
        dataBinding.setConditionText(conditionText);

        if(haveMenu.get() && haveMenuImage.get()){
            dataBinding.menuImage.setImageDrawable(menuDrawable);
        }

        if(haveCondition.get() && haveArrow.get()){
            dataBinding.arrowImage.setImageDrawable(arrowDrawable);
        }

        dataBinding.menuText.setTextColor(menuTextColor);
        dataBinding.conditionText.setTextColor(conditionTextColor);
        dataBinding.lineText.setBackgroundColor(menuLineColor);
        dataBinding.conditionLine.setBackgroundColor(conditionLineColor);
        dataBinding.searchtextLayout.setBackgroundDrawable(searchBg);
    }
}
