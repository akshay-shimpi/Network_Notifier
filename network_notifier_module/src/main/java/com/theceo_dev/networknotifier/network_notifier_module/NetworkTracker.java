package com.theceo_dev.networknotifier.network_notifier_module;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTracker {
    public static final String MOBILE_WIFI = "Online";
    public static final String NO_INTERNET_CONNECTION = "No internet connection";

    public static String checkNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.isConnected() && networkInfo != null) {
                NetworkLayout.haveNetworkConnection = true;
                return MOBILE_WIFI;
            }
        }
        NetworkLayout.haveNetworkConnection = false;
        return NO_INTERNET_CONNECTION;
    }
}
