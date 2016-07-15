package com.example.huson.husondevandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 当前类注析：
 * Created by huson on 2016/7/14.
 * 940762301@qq.com
 */
public class IsNetworkAvailable {
    private static final int CMNET=3;
    private static final int CMWAP=2;
    private static final int WIFI=1;

    public static boolean isNetworkAvailable(Context mcontext)
    {
        Context context = mcontext.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取当前的网络状态  -1：没有网络  1：WIFI网络 2：wap网络 3：net网络
     * @param context
     * @return
     */
    public static int getAPNType(Context context){
        int netType = -1;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo==null){
            return netType;
        }
        int nType = networkInfo.getType();
        if(nType==ConnectivityManager.TYPE_MOBILE){
            DebugLog.e("networkInfo.getExtraInfo() is "+networkInfo.getExtraInfo());
            if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){
                netType = CMNET;
            }
            else{
                netType = CMWAP;
            }
        }
        else if(nType==ConnectivityManager.TYPE_WIFI){
            netType = WIFI;
        }
        return netType;
    }
}

