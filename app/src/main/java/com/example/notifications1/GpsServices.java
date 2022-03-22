package com.example.notifications1;

import static com.example.notifications1.NotificationChannels.ID_GPS_ENABLED;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class GpsServices extends Service {
    GpsReceiver gpsReceiver = new GpsReceiver();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter gps = new IntentFilter("android.location.PROVIDERS_CHANGED");
        registerReceiver(gpsReceiver, gps);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(gpsReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}