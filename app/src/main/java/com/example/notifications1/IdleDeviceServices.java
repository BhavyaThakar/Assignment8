package com.example.notifications1;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class IdleDeviceServices extends Service {

    IdleDeviceReceiver idleDeviceReceiver = new IdleDeviceReceiver();

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        registerReceiver(idleDeviceReceiver, new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(idleDeviceReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}