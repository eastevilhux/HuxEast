package com.god.uikit.widget

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.god.uikit.R
import com.god.uikit.databinding.LayoutIcontextimageBinding
import com.god.uikit.utils.dip2Px

class IconTextImageLayout : FrameLayout{
    var iconWidht = 30;
    var iconHeight = 30;
    var haveIcon = ObservableField<Boolean>(false);

    var haveTitle = ObservableField<Boolean>(false);
    var titleText = ObservableField<String>();

    var haveSubtitle = ObservableField<Boolean>(true);
    var subtitleText = ObservableField<String>();

    var haveContent = ObservableField<Boolean>(false);
    var contentText = ObservableField<String>();

    var haveArrow = ObservableField<Boolean>(false);

    lateinit var tempBd : Drawable;
    var arrowBd : Drawable? = null;

    lateinit var dataBinding : LayoutIcontextimageBinding;

    var iconBip : Drawable? = null;
    var defTitleColor = ContextCompat.getColor(context,R.color.colorInfo);
    var defSubtitleColor = ContextCompat.getColor(context,R.color.colorApp);
    var titleColor = defTitleColor;
    var subtitleColor = defSubtitleColor;
    var contentColor = defTitleColor;

    constructor(context: Context?) : super(context!!){};

    constructor(
        context: Context?,
        attributeSet: AttributeSet?
    ) : super(context!!, attributeSet) {
        var ta = context!!.obtainStyledAttributes(attributeSet,R.styleable.IconTextImageLayout);
        haveIcon.set(ta.getBoolean(R.styleable.IconTextImageLayout_isHaveIcon,false));
        if(haveIcon.get()!!){
            tempBd = BitmapDrawable(
                context.resources,
                BitmapFactory.decodeResource(resources, R.drawable.icon_no_data_default)
            )
            iconWidht = ta.getDimension(R.styleable.IconTextImageLayout_icon_width,iconWidht.toFloat()).toInt();
            iconHeight = ta.getDimension(R.styleable.IconTextImageLayout_icon_width,iconHeight.toFloat()).toInt();
            var iconSrc = ta.getDrawable(R.styleable.IconTextImageLayout_icon_src);
            iconBip = iconSrc
                ?.let { iconSrc}
                ?: tempBd;
        }

        haveTitle.set(ta.getBoolean(R.styleable.IconTextImageLayout_isHaveTitle,false));
        if(haveTitle.get()!!){
            titleColor = ta.getColor(R.styleable.IconTextImageLayout_title_color, defTitleColor);
            titleText.set(ta.getString(R.styleable.IconTextImageLayout_title_text));
        }
        haveSubtitle.set(ta.getBoolean(R.styleable.IconTextImageLayout_isHaveSubtitle,false));
        if(haveSubtitle.get()!!){
            subtitleColor = ta.getColor(R.styleable.IconTextImageLayout_subtitle_color,defSubtitleColor);
            subtitleText.set(ta.getString(R.styleable.IconTextImageLayout_subtitle_text));
        }

        haveContent.set(ta.getBoolean(R.styleable.IconTextImageLayout_haveContent,false));
        if(haveContent.get()!!){
            contentText.set(ta.getString(R.styleable.IconTextLayout_contextText));
            contentColor = ta.getColor(R.styleable.IconTextLayout_contextTextColor,defTitleColor);
        }
        haveArrow.set(ta.getBoolean(R.styleable.IconTextImageLayout_have_arrow,false));
        if(haveArrow.get()!!){
            var defArrow = BitmapDrawable(
                context.resources,
                BitmapFactory.decodeResource(resources, R.drawable.icon_arrow_def)
            )
            arrowBd = ta.getDrawable(R.styleable.IconTextImageLayout_arrow_src);
            arrowBd = arrowBd?.let { arrowBd } ?: defArrow;
        }
        ta.recycle();
        ta = null;
        initView();
    }

    protected fun initView(){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.layout_icontextimage,this,true);
        dataBinding.haveContent = haveContent;
        dataBinding.haveIcon = haveIcon;
        dataBinding.haveSubtitle = haveSubtitle;
        dataBinding.haveTitle = haveTitle;
        dataBinding.haveArrow = haveArrow;
        dataBinding.arrowImageview.setImageDrawable(arrowBd);
        if(haveIcon.get()!!){
            dataBinding.srcImageview.setImageDrawable(iconBip);
        }
        var params = dataBinding.srcImageview.layoutParams;
        params.height = iconHeight.dip2Px();
        params.width = iconWidht.dip2Px();
        dataBinding.titleText = titleText;
        dataBinding.subTitle = subtitleText;
        dataBinding.contentText = contentText;
    }
}