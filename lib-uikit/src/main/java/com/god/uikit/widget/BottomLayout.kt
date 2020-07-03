package com.god.uikit.widget

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.databinding.ObservableMap
import com.god.uikit.R
import com.god.uikit.databinding.LayoutBottomBinding

class BottomLayout : FrameLayout {
    lateinit var dataBinding : LayoutBottomBinding;

    lateinit var imageMap : ObservableMap<String,Drawable>;
    lateinit var itemNumbers : ObservableField<Int>;

    private var iconSel1: Drawable? = null
    private var iconSel2: Drawable? = null
    private var iconSel3: Drawable? = null
    private var iconSel4: Drawable? = null
    private var iconSel5: Drawable? = null

    private var iconNor1: Drawable? = null
    private var iconNor2: Drawable? = null
    private var iconNor3: Drawable? = null
    private var iconNor4: Drawable? = null
    private var iconNor5: Drawable? = null

    private var text1: String? = null
    private var text2: String? = null
    private var text3: String? = null
    private var text4: String? = null
    private var text5: String? = null

    private var selColor = 0

    private var norColor = 0

    private var lineColor = 0;

    //选中的导航栏，默认从1开始
    private var index: ObservableField<Int>? = null

    //底部文字
    private var textMap: ObservableArrayMap<String, String>? = null

    private var onBottomClickListener: OnBottomClickListener? = null

    private var haveLine = ObservableField<Boolean>(false);

    constructor(context: Context) : super(context) {}
    constructor(
        context: Context?,
        attributeSet: AttributeSet?
    ) : super(context!!, attributeSet) {
        var ta = context.obtainStyledAttributes(attributeSet, R.styleable.BottomLayout);

        haveLine.set(ta.getBoolean(R.styleable.BottomLayout_haveLine,false));

        textMap = ObservableArrayMap<String, String>()
        text1 = ta.getString(R.styleable.BottomLayout_itemText_1)
        text2 = ta.getString(R.styleable.BottomLayout_itemText_2)
        text3 = ta.getString(R.styleable.BottomLayout_itemText_3)
        textMap!!["text1"] = text1
        textMap!!["text2"] = text2
        textMap!!["text3"] = text3

        //默认3个底部导航栏
        itemNumbers = ObservableField(ta.getInt(R.styleable.BottomLayout_itemNumbers,3));

        val tempBtp: Drawable = BitmapDrawable(
            BitmapFactory.decodeResource(
                resources,
                R.drawable.icon_no_data_default
            )
        )
        imageMap = ObservableArrayMap();

        var bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_sel_1)
        if(bd == null){
            iconSel1 = tempBtp;
        }else{
            iconSel1 = bd;
        }
        bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_nor_1);
        if(bd == null){
            iconNor1 = tempBtp;
        }else{
            iconNor1 = bd;
        }

        bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_sel_2);
        if(bd == null){
            iconSel2 = tempBtp;
        }else{
            iconSel2 = bd;
        }

        bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_nor_2);
        if(bd == null){
            iconNor2 = tempBtp;
        }else{
            iconNor2 = bd;
        }

        bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_sel_3);
        if(bd == null){
            iconSel3 = tempBtp;
        }else{
            iconSel3 = bd;
        }

        bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_nor_3);
        if(bd == null){
            iconNor3 = tempBtp;
        }else{
            iconNor3 = bd;
        }
        imageMap["icon1sel"] = iconSel1;
        imageMap["icon2sel"] = iconSel2;
        imageMap["icon3sel"] = iconSel3;
        imageMap["icon1nor"] = iconNor1;
        imageMap["icon2nor"] = iconNor2;
        imageMap["icon3nor"] = iconNor3;

        if(itemNumbers.get()!! > 3) {
            bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_sel_4);
            if(bd == null){
                iconSel4 =  tempBtp;
            }else{
                iconSel4 = bd;
            }
            imageMap["icon4sel"] = iconSel4;
            bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_nor_4);
            if(bd == null){
                iconNor4 = tempBtp;
            }else{
                iconNor4 = bd;
            }
            imageMap["icon4nor"] = iconNor4;

            text4 = ta.getString(R.styleable.BottomLayout_itemText_4)
            textMap!!["text4"] = text4
        }

        if(itemNumbers.get()!! > 4){
            bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_sel_5);
            if(bd == null){
                iconSel5 = tempBtp;
            }else{
                iconSel5 = bd;
            }
            imageMap["icon5sel"] = iconSel5

            bd = ta.getDrawable(R.styleable.BottomLayout_iconSrc_nor_5);
            if(bd == null){
                iconNor5 = tempBtp;
            }else{
                iconNor5 = bd;
            }
            imageMap["icon5nor"] = iconNor5

            text5 = ta.getString(R.styleable.BottomLayout_itemText_5)
            textMap!!["text5"] = text5
        }

        selColor = ta.getColor(
            R.styleable.BottomLayout_selTextColor,
            resources.getColor(R.color.colorApp)
        )
        norColor = ta.getColor(
            R.styleable.BottomLayout_norTextColor,
            resources.getColor(R.color.colorHint)
        )

        lineColor = ta.getColor(R.styleable.BottomLayout_lineColor,
            resources.getColor(R.color.colorLineLight));

        ta.recycle();
        ta = null;
        initAttributeSet()
    }

    fun initAttributeSet(){
        dataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_bottom,
            this,
            true
        )
        dataBinding.bottomLayout = this
        dataBinding.haveLine = haveLine;
        if(haveLine.get()!!){
            dataBinding.lineView.setBackgroundColor(lineColor);
        }
        dataBinding.imageMap = imageMap
        dataBinding.itemNumbers = itemNumbers

        dataBinding.norTextColor = norColor
        dataBinding.selTextColor = selColor

        dataBinding.textMap = textMap

        index = ObservableField(1)
        dataBinding.itemIndex = index
    }

    /**
     * 底部点击事件处理
     *
     * create by Administrator at 2020/3/27 3:28
     * @author Administrator
     * @since 1.0.0
     * @param view 触发点击事件的View
     * @return
     * void
     */
    fun onBottomClick(view: View) {
        val id = view.id
        if (id == R.id.item_layout_1) {
            index!!.set(1)
        } else if (id == R.id.item_layout_2) {
            index!!.set(2)
        } else if (id == R.id.item_layout_3) {
            index!!.set(3)
        } else if (id == R.id.item_layout_4) {
            index!!.set(4)
        } else if (id == R.id.item_layout_5) {
            index!!.set(5)
        }
        if (onBottomClickListener != null) {
            index!!.get()?.let {
                onBottomClickListener!!.onBottomClick(it)
            }
        }
    }

    fun setOnBottomClickListener(onBottomClickListener: OnBottomClickListener) {
       this.onBottomClickListener = onBottomClickListener;
    }

    fun twoAndThree(operation: (Int, Int) -> Int){
        //调用函数类型的参数
        val result = operation(2, 3)
        println("The result is $result")
    }

    fun main(arg: Array<String>) {
        twoAndThree{ a, b -> a + b}
        twoAndThree{ a, b -> a * b}
    }


    fun getOnBottomClickListener(): OnBottomClickListener? {
        return onBottomClickListener
    }

    interface OnBottomClickListener {
        /**
         * 底部导航栏点击事件监听
         * @author hux
         * @createTime 2019/5/5 16:15
         * @since 1.0.0
         * @see BottomLayout
         *
         * @param position
         * 选中的底部导航栏下标，从1开始
         * @return
         * void
         */
        fun onBottomClick(position: Int)
    }
}