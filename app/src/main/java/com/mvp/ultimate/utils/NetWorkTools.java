package com.mvp.ultimate.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkTools {
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected() && info.isAvailable();
    }

    public static int getType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info == null ? -1 : info.getType();
    }

    public static boolean isWifi(Context context) {
        return getType(context) == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isMobile(Context context) {
        return getType(context) == ConnectivityManager.TYPE_MOBILE;
    }
}
