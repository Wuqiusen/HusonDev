package com.example.mylibrary.utils;

import android.app.Application;
import android.content.Context;


/**
 * 当前类注析：
 * Created by huson on 2016/7/14.
 * 940762301@qq.com
 */
public class GuideUtils {
    private static final  String KEY_GUIDE_ACTIVITY="key_guide_activity";
    /**
     * 根据版本code值 判断是否已经引导过了
     * @param context  上下文
     * @param versionCode  版本值
     * @return  引导过返回true,否则返回false
     */
    public static boolean activityIsGuided(Context context, int versionCode, Application application){
        SharedPreferencesHelper mSharedPreferencesHelper=SharedPreferencesHelper.getInstance(application);
        if(mSharedPreferencesHelper.getIntValue(KEY_GUIDE_ACTIVITY)==versionCode){
            return true;
        }
        return false;
    }
    /**
     * 设置code值，表明已经引导过
     * @param context
     * @param versionCode
     */
    public static void setIsGuided(Context context,int versionCode, Application application){
        SharedPreferencesHelper mSharedPreferencesHelper=SharedPreferencesHelper.getInstance(application);
        mSharedPreferencesHelper.putIntValue(KEY_GUIDE_ACTIVITY, versionCode);
    }
}

