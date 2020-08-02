package com.good.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * TO DO
 * createTime : 2018/4/2 11:38
 * update by admin on 2018/4/2.
 * version : $VARIABLE_NAME
 *
 * @see
 */

public class SharedPreferencesUtil {
    public static final String mTAG = "hux_east_app";

    private SharedPreferences.Editor mEditor;

    private static SharedPreferences mPreferences;
    private static SharedPreferencesUtil mSharedPreferencesUtil;

    private SharedPreferencesUtil(Context context) {
        mPreferences =   context.getSharedPreferences(mTAG, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        synchronized (SharedPreferencesUtil.class){
            if (mSharedPreferencesUtil ==null){
                mSharedPreferencesUtil =new SharedPreferencesUtil(context);
            }
            return  mSharedPreferencesUtil;
        }
    }

    /**
     * 保存数据到本地SharedPreferences
     * @author hux
     * @createTime 2018/4/2 11:59
     * @since 0.0.1
     * @param key
     *      保存数据标识
     * @param value
     *      值
     */
    public void saveData(String key, String value){
        if(mEditor == null)
            mEditor = mPreferences.edit();
        mEditor.putString(key,value);
        mEditor.commit();
    }

    /**
     * 保存数据到本地SharedPreferences
     * @author hux
     * @createTime 2018/4/2 11:59
     * @since 0.0.1
     * @param key
     *      保存数据标识
     * @param object
     *      值
     */
    public void saveData(String key, Object object){
        if(mEditor == null)
            mEditor = mPreferences.edit();
        if (object instanceof String) {
            mEditor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            mEditor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            mEditor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            mEditor.putLong(key, (Long) object);
        } else {
            mEditor.putString(key, object.toString());
        }
        mEditor.commit();
    }

    /**
     * 获取保存到SharedPreferences的数据
     * @author hux
     * @createTime 2018/4/2 12:02
     * @since 0.0.1
     * @param key
     *      保存时的key
     * @param defaultObject
     *      默认值
     * @return
     *      Object
     */
    public Object getData(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return mPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return mPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return mPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return mPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return mPreferences.getLong(key, (Long) defaultObject);
        } else {
            return mPreferences.getString(key, null);
        }
    }

    /**
     * 获取保存的SharedPreferences中的String数据
     * @author hux
     * @createTime 2018/4/2 12:03
     * @since 0.0.1
     * @param key
     *      保存时的key
     * @return
     *      String
     */
    public String getStringData(String key){
        return mPreferences.getString(key,null);
    }

    public int getIntData(String key){
        return mPreferences.getInt(key,0);
    }

    public int getIntData(String key,int defvalue){
        return mPreferences.getInt(key,defvalue);
    }

    /**
     * 获取保存的SharedPreferences中的String数据
     * @author hux
     * @createTime 2018/4/2 12:04
     * @since 0.0.1
     * @param key
     *      保存时的key
     * @return
     *      String
     */
    public String getStringData(String key, String defaultValue){
        return mPreferences.getString(key,defaultValue);
    }



}
