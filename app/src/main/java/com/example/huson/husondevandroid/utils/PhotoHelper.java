package com.example.huson.husondevandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.huson.husondevandroid.R;
import com.example.huson.husondevandroid.application.Constants;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 当前类注析：图片操作工具类，拍照保存，显示图片，压缩图片
 * 用法：
 * 拍照photoHelper.takePhoto();
 * 压缩图片PhotoHelper.setPath(photoFile);
 * Created by huson on 2016/5/27.
 * 940762301@qq.com
 */
public class PhotoHelper {

    private final static String TIMESTAMP_FORMAT = "yyyy_MM_dd_HH_mm_ss";

    public final static int CAPTURE_PHOTO_REQUEST_CODE = 0x1111;

    private Activity mActivity;
    /**
     * 存放图片的目录
     */
    private File mPhotoFolder;
    /**
     * 拍照生成的图片文件
     */
    private File mPhotoFile;

    /**
     * @param activity
     * @param photoFolder 存放生成照片的目录，目录不存在时候会自动创建，但不允许为null;
     */
    public PhotoHelper(Activity activity, File photoFolder) {
        this.mActivity = activity;
        this.mPhotoFolder = photoFolder;
    }

    /**
     * 拍照
     */
    public void takePhoto() {
        if (hasCamera()) {
            createPhotoFile();

            if (mPhotoFile == null) {
                Toast.makeText(mActivity, R.string.camera_open_error, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri fileUri = Uri.fromFile(mPhotoFile);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            mActivity.startActivityForResult(captureIntent, CAPTURE_PHOTO_REQUEST_CODE);

        } else {
            Toast.makeText(mActivity, R.string.camera_open_error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 拍照
     */
    public void takePhoto(int REQUEST_CODE) {
        if (hasCamera()) {
            createPhotoFile();

            if (mPhotoFile == null) {
                Toast.makeText(mActivity, R.string.camera_open_error, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri fileUri = Uri.fromFile(mPhotoFile);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            mActivity.startActivityForResult(captureIntent, REQUEST_CODE);

        } else {
            Toast.makeText(mActivity, R.string.camera_open_error, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 创建照片文件
     */
    private void createPhotoFile() {
        if (mPhotoFolder != null) {
            if (!mPhotoFolder.exists()) {//检查保存图片的目录存不存在
                mPhotoFolder.mkdirs();
            }

            String fileName = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date());
            mPhotoFile = new File(mPhotoFolder, fileName + BitmapUtils.JPG_SUFFIX);
            if (mPhotoFile.exists()) {
                mPhotoFile.delete();
            }
            try {
                mPhotoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                mPhotoFile = null;
            }
        } else {
            mPhotoFile = null;
            Toast.makeText(mActivity, R.string.not_specify_a_directory, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 判断系统中是否存在可以启动的相机应用
     *
     * @return 存在返回true，不存在返回false
     */
    public boolean hasCamera() {
        PackageManager packageManager = mActivity.getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * 获取当前拍到的图片文件
     *
     * @return
     */
    public File getPhoto() {
        return mPhotoFile;
    }

    /**
     * 设置照片文件
     *
     * @param photoFile
     */
    public void setPhoto(String photoFile) {
        File  file = new File(photoFile);
        this.mPhotoFile = file;
    }

    /**
     * 获取图片名
     */
    public String  getPhotoName(){
        return mPhotoFile.getName();
    }

    /**
     * 根据imageview宽高显示图片
     * @param imageView
     * @return
     */
    public void showPhoto(Context context,ImageView imageView,String file) {
        imageView.getWidth();
        int requestWidth = 180;
        int requestHeight = 60;
        DebugLog.e("width" + imageView.getWidth());
        Bitmap bitmap = BitmapFactory.decodeFile(file);
        if (bitmap != null) {
            DebugLog.e("++++++++++++++++++++++++++++++++++bitmap");
            int degree = BitmapUtils.getBitmapDegree(file);//检查是否有被旋转，并进行纠正
            if (degree != 0) {
                bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, degree);
            }
//            // 下面这两句是对图片按照一定的比例缩放，这样就可以完美地显示出来。
            int scale = ImageThumbnailUtils.reckonThumbnail(bitmap.getWidth(), bitmap.getHeight(), requestWidth, requestHeight);
            Bitmap bitMap = ImageThumbnailUtils.PicZoom(bitmap, bitmap.getWidth() / scale, bitmap.getHeight() / scale);
//            //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
//            bitmap.recycle();
            imageView.setImageBitmap(bitMap);
        }
    }
    /**
     * 根据imageview宽高显示图片
     * @return
     */
    public Bitmap getPhoto(Context context,String file) {
        int requestWidth = 180;
        int requestHeight = 60;
        Bitmap bitmap = BitmapFactory.decodeFile(file);
        if (bitmap != null) {
            DebugLog.e("++++++++++++++++++++++++++++++++++bitmap");
            int degree = BitmapUtils.getBitmapDegree(file);//检查是否有被旋转，并进行纠正
            if (degree != 0) {
                bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, degree);
            }
//            // 下面这两句是对图片按照一定的比例缩放，这样就可以完美地显示出来。
            int scale = ImageThumbnailUtils.reckonThumbnail(bitmap.getWidth(), bitmap.getHeight(), requestWidth, requestHeight);
            Bitmap bitMap = ImageThumbnailUtils.PicZoom(bitmap, bitmap.getWidth() / scale, bitmap.getHeight() / scale);
//            //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
//            bitmap.recycle();
            return bitMap;
        }
        return null;
    }

    /**
     * 根据屏幕宽高显示图片
     * @param context
     */
    public void showPhoto(Context context) {
        int requestWidth = (int) (RuleUtils.getScreenWidth(context) * 0.10);
        int requestHeight = (int) (RuleUtils.getScreenHeight(context) * 0.10);
        Bitmap bitmap = BitmapUtils.decodeBitmapFromFile(mPhotoFile, requestWidth, requestHeight);//按照屏幕宽高的3/4比例进行缩放显示
        if (bitmap != null) {
            int degree = BitmapUtils.getBitmapDegree(mPhotoFile.getAbsolutePath());//检查是否有被旋转，并进行纠正
            if (degree != 0) {
                bitmap = BitmapUtils.rotateBitmapByDegree(bitmap, degree);
            }
        }
    }

    public void savePhoto(Bitmap bm,String fileName, Context context){
        File myCaptureFile = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断SD卡是否挂载
            File foder = new File(Environment.getExternalStorageDirectory(), Constants.PATH.SECONDPATH + "/" + Constants.PATH.PHOTO_FOLDER_NAME + "/");
            myCaptureFile = new File(foder,  fileName);
            if (!foder.exists()) {
                foder.mkdirs();
            }
        }
        if (!myCaptureFile.exists()) {
            try {
                myCaptureFile.createNewFile();
                FileOutputStream bos = new FileOutputStream(myCaptureFile);
                bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        context.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦

    }

    public static String setPath(String photoPath) {
        File file = new File(photoPath);
        Log.w("tag", file.length()+"  未压缩");
        Bitmap resizeBmp = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 数字越大读出的图片占用的heap越小 不然总是溢出
        if (file.length() < 20480) { // 0-20k
            opts.inSampleSize = 1;
        } else if (file.length() < 51200) { // 20-50k
            opts.inSampleSize = 1;
        } else if (file.length() < 307200) { // 50-300k
            opts.inSampleSize = 1;
        } else if (file.length() < 819200) { // 300-800k
            opts.inSampleSize = 2;
        } else if (file.length() < 1048576) { // 800-1024k
            opts.inSampleSize = 4;
        } else if (file.length() < 2097152) { // 1M - 2M
            opts.inSampleSize = 5;
        }else if (file.length() < 3145728) { // 2M - 3M
            opts.inSampleSize = 6;
        }else{
            opts.inSampleSize = 7;
        }
        resizeBmp = BitmapFactory.decodeFile(file.getPath(), opts);

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        resizeBmp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        Log.i("tag", bytes.length+"压缩后");

        file.delete();
        file = new File(photoPath);
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photoPath;
    }

}
