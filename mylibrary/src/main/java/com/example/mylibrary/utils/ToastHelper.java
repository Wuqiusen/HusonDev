package com.example.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastHelper {
    public static Toast mToast;

    public static void showToast(int resId, Context context) {
        String text = context.getString(resId);
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(String text, Context context) {
        try{
            if (mToast == null) {
                if(context == null){
                    return;
                }
                mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
        }catch (Exception e){
//            ExceptionUploadUtils.sendTryCatchException(context,1000,e);
        }
    }
}
