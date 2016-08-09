package com.example.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 当前类注析：当前为SharedPerferences进行封装基本的方法,SharedPerferences已经封装成单例模式
 * 可以通过SharedPreferences sp=SharedPreferencesHelper.getInstances(FDApplication.getInstance())进行获取当前对象
 * sp.putStringValue(key,value)进行使用
 * Created by huson on 2016/7/14.
 * 940762301@qq.com
 */
public class SharedPreferencesHelper {
    private final static String CACHE_FILE_NAME = "com.example.huson.husondevandroid";
    private static SharedPreferencesHelper instance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static SharedPreferencesHelper getInstance(Context context) {
        if (instance == null && context != null) {
            instance = new SharedPreferencesHelper(context);
        }
        return instance;
    }

    private SharedPreferencesHelper(Context context) {
        sp = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void cacheErrorLog(Context mContext,String errorLog){
        editor.putString("errorLog",errorLog);
//        editor.putString("errorLogPhone", userPhone);
        editor.commit();
    }

    public String getStringValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, null);
        }
        return null;
    }

    public int getIntValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }

    public int getIntValueByDefault(String key)
    {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }
    public boolean getBooleanValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, false);
        }
        return true;
    }

    public float getFloatValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getFloat(key, 0);
        }
        return 0;
    }

    public void putStringValue(String key, String value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public void putIntValue(String key, int value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public void putBooleanValue(String key, boolean value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void putLongValue(String key, long value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public void putFloatValue(String key, Float value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }


}

