package com.example.huson.husondevandroid.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;

import com.example.huson.husondevandroid.application.Constants;
import com.example.huson.husondevandroid.widget.MyDialog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * 当前类注析：
 * Created by huson on 2016/7/27.
 * 940762301@qq.com
 */
public class DownLoadAndSetUpAPK extends AsyncTask<String, Integer, byte[]> {
    private Activity mActivity;
    private MyDialog progressDialog;
    private String downUrl;
    private boolean isLoadAll = false;
    private boolean isLoadSuccess = true;
    private HttpURLConnection httpURLConnection;


    public void DownLoadAndSetUpAPK(Activity activity, String downUrl) {
        mActivity = activity;
        this.downUrl = downUrl;
        execute(downUrl);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showDialog();
    }

    @Override
    protected byte[] doInBackground(String... params) {
        /* 所下载文件的URL */
        try {
            URL url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
             /* URL属性设置 */
            httpURLConnection.setRequestMethod("GET");
           /* URL建立连接 */
            httpURLConnection.connect();
            /* 下载文件的大小 */
            int fileOfLength = httpURLConnection.getContentLength();
            /* 每次下载的大小与总下载的大小 */
            int totallength = 0;
            int length = 0;
            /* 输入流 */
            InputStream in = httpURLConnection.getInputStream();
            /* 输出流 */
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断SD卡是否挂载
                File foder = new File(Environment.getExternalStorageDirectory(), Constants.PATH.SECONDPATH + "/");
                File file = new File(foder, Constants.PATH.APKNAME);
                if (!foder.exists()) {
                    foder.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(in);

            /* 缓存模式，下载文件 */
                byte[] buff = new byte[1024];
                while ((length = in.read(buff)) != -1) {
                    totallength += length;
                    int str1 = (int) ((totallength * 100) / fileOfLength);
                    publishProgress(str1);
                    fos.write(buff, 0, length);
                    fos.flush();
                }
            /* 关闭输入输出流 */
                in.close();
                fos.close();
                bis.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
        new SetUpAPK().setUpAPK(mActivity);

    }

    private void showDialog() {
        progressDialog = new MyDialog(mActivity, "正在下载，请稍后...", "", MyDialog.PROGRESS);
        progressDialog.show();
        progressDialogListener();
    }



    private void progressDialogListener() {
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!isLoadAll && isLoadSuccess) {
                    showTipDialog();

                }
            }
        });
    }

    private void showTipDialog() {
        final MyDialog tipDialog = new MyDialog(mActivity, "提示", "确定要放弃更新？", MyDialog.HAVEBUTTON);
        tipDialog.show();
        tipDialog.setCancelable(false);
        tipDialog.ButtonQuery(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipDialog.dismiss();
                httpURLConnection.disconnect();
            }
        });

        tipDialog.ButtonCancel(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipDialog.dismiss();
                progressDialog.show();
            }
        });

    }
}
