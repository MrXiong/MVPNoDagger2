package com.mvp.ultimate.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mvp.ultimate.base.IApplication;

/**
 * Created by codeest on 2016/8/4.
 */
public class SystemUtil {

    /**
     * 检查WIFI是否连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) IApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }
    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) IApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }
    /**
     * 检查是否有可用网络
     */
    @SuppressLint("MissingPermission")
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) IApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

}
