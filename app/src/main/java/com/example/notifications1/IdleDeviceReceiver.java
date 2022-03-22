package com.example.notifications1;

import static com.example.notifications1.NotificationChannels.ID_DEVICE_CHARGING;
import static com.example.notifications1.NotificationChannels.ID_DEVICE_IDLE;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class IdleDeviceReceiver extends BroadcastReceiver {

    @RequiresApi (api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager.isDeviceIdleMode()){
            Intent intentForIdleDevice = new Intent(context, IdleDeviceDestinationActivity.class);
            intentForIdleDevice.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentForIdleDevice = PendingIntent.getActivity(context, 52, intentForIdleDevice, 0);

            String title = "Idle Device";
            String message = "Device is Idle";
            Notification notification  = new NotificationCompat.Builder(context, ID_DEVICE_IDLE)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_baseline_power_24)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                    .setContentIntent(pendingIntentForIdleDevice)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(12, notification);
        }
        else {
            Intent intentForGps = new Intent(context, GpsDestinationActivity.class);
            intentForGps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentForGps = PendingIntent.getActivity(context, 52, intentForGps, 0);

            String title = "Idle Device";
            String message = "Device is Idle";
            Notification notification  = new NotificationCompat.Builder(context, ID_DEVICE_IDLE)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_baseline_power_24)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                    .setContentIntent(pendingIntentForGps)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(12, notification);
        }

    }
}