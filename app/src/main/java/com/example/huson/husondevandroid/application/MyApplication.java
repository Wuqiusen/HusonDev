package com.example.huson.husondevandroid.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.text.TextUtils;

import com.example.huson.husondevandroid.sharepreference.SharedPreferencesHelper;
import com.example.huson.husondevandroid.utils.DebugLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * 当前类注析：
 * Created by huson on 2016/7/14.
 * 940762301@qq.com
 */
public class MyApplication  extends Application {

    private static MyApplication singleInstance;
    public static Context mContext;
    private static SharedPreferencesHelper spHelper ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
//        捕捉未知异常
        Thread.currentThread().setUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler());
//        DebugLog.i("====重新打开");

        singleInstance = this;
       spHelper = SharedPreferencesHelper.getInstance(getApplicationContext());
    }


    public static MyApplication getSingleInstance() {

        return singleInstance;
    }

    private class MyUncaughtExceptionHandler implements
            Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            // TODO Auto-generated method stub
            // 获取异常信息 （补救）
            // 把异常写到文件里 放到sd卡
            // 服务器上传
            try {
                // byte[] file String

                StringWriter sw = new StringWriter();
                PrintWriter err = new PrintWriter(sw);

                Field[] fields = Build.class.getFields();
                for (Field f : fields) {
                    sw.write(f.getName() + ":" + f.get(null) + "\n");// 静态属性
                    // 不需要对象
                }

                ex.printStackTrace(err);
                String errorLog = sw.toString();
                spHelper.cacheErrorLog(MyApplication.this, errorLog);

                //保存到本地
                String filePath = Environment.getExternalStorageDirectory()+"/"+ Constants.PATH.SECONDPATH+"/Test/";
                String fileName = "log.txt";
                writeTxtToFile(errorLog, filePath, fileName);

                sw.close();
                err.close();
                PackageManager pm = getPackageManager();
                Intent intent = pm.getLaunchIntentForPackage("zxzs.ppgj");
                startActivity(intent);
                // 自杀 重生
                android.os.Process.killProcess(android.os.Process.myPid());

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    // 将字符串写入到文本文件中
    public void writeTxtToFile(String strcontent, String filePath, String fileName) {
        makeFilePath(filePath, fileName);
        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                DebugLog.d("Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            DebugLog.e("Error on write File:" + e);
        }
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            DebugLog.i(e + "");
        }

    }
}
