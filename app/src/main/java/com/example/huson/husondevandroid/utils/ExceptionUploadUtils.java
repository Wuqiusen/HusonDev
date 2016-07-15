package com.example.huson.husondevandroid.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.huson.husondevandroid.sharepreference.SharedPreferencesHelper;
import com.example.huson.husondevandroid.sharepreference.SpHelperTag;

import java.text.SimpleDateFormat;


/**
 * Created by Administrator on 2015/12/31.
 */
public class ExceptionUploadUtils {

    private static String methodName;
    private static int currentCode;
    private static PackageManager pm;

    private static void getMethodNames(StackTraceElement[] sElements) {
        methodName = sElements[1].getMethodName();
    }
    private static void getAppVersion(Context context) {
        pm = context.getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            currentCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void sendTryCatchException(Context context ,String className , Exception e){
        getMethodNames(new Throwable().getStackTrace());
        getAppVersion(context);
        String errorLog = "\n"+"className:"+className+"\n"
                +"methodName:"+methodName+"\n"
                +"version:"+currentCode+"\n"
                +e.toString()+"\n";

        SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance(context);

        String userPhone = sharedPreferencesHelper.getStringValue(SpHelperTag.USER);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        //上传异常
//        HttpUtils utils = new HttpUtils();
//        RequestParams params = new RequestParams();
//        params.addBodyParameter("log", errorLog);//异常报告内容
//        params.addBodyParameter("phone", userPhone);//用户Phone 可以为空
//        params.addBodyParameter("key", MD5.MD5Encode(df.format(new Date())));//当前系统时间

    }
}
