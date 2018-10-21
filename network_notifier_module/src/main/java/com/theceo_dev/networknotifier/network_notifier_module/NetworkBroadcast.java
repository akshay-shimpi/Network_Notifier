package com.theceo_dev.networknotifier.network_notifier_module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class NetworkBroadcast  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
            String Status = NetworkTracker.checkNetwork(context);
            if (Status.length() > 0) {
                NetworkListener networkListener = (NetworkListener) NetworkLayout.networkListener;
                if (networkListener  != null) {
                    networkListener.OnNetworkChanged(Status);
                }
            }
        }
    }
}