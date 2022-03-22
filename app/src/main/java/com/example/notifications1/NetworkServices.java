package com.example.notifications1;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class NetworkServices extends Service {
    public NetworkServices() {
    }
    NetworkReceiver networkReceiver = new NetworkReceiver();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGED"));

        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
    return null;
    }
}



