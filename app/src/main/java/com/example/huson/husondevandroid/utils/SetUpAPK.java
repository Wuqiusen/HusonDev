package com.example.huson.husondevandroid.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.example.huson.husondevandroid.application.Constants;

import java.io.File;


/**
 * 当前类注析：
 * Created by huson on 2016/7/27.
 * 940762301@qq.com
 */
public class SetUpAPK {

    public void setUpAPK(Activity activity){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/" +
                Constants.PATH.SECONDPATH, Constants.PATH.APKNAME)), "application/vnd.android.package-archive");
        activity.startActivityForResult(intent, 0);
        activity.finish();
    }
}
