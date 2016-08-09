package com.example.mylibrary.utils;

import android.util.Log;

/**
 * 当前类注析：
 * Created by huson on 2016/7/14.
 * 940762301@qq.com
 */
public class ClickUtil {

    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        Log.i("log",""+timeD);
        return timeD <= 100;
    }
}