package com.example.mylibrary.utils;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * 当前类注析：解析gson工具类
 * 用法：
 * BaseBean baseBean = MyGsonUtils.fromJson(returnData, BaseBean.class);
 * Created by Administrator on 2015/11/2.
 */
public class MyGsonUtils {
    public static Gson gson;

    public static <T> T fromJson(Context context ,String result,Class<T> classOfT){
        T o = null;
        if(gson == null){
            gson = new Gson();
        }
        try {
            o = gson.fromJson(result, (Type) classOfT);
        }catch (Exception e){
            return null;
        }
        return o;
    }
    public static <T> T fromJson(String result,Class<T> classOfT){
        T o = null;
        if(gson == null){
            gson = new Gson();
        }
        try {
            o = gson.fromJson(result, (Type) classOfT);
        }catch (Exception e){
            return null;
        }
        return o;
    }
}
