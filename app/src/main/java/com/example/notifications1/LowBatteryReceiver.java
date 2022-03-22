package com.example.notifications1;

import static com.example.notifications1.NotificationChannels.ID_LOW_BATTERY;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class LowBatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentForLowBattery = new Intent(context, LowBatteryDestinationActivity.class);
        intentForLowBattery.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntentForLowBattery = PendingIntent.getActivity(context, 22, intentForLowBattery, 0);

        String title = "Low Battery";
        String message = "Device is running out of Battery";

        Notification notification = new NotificationCompat.Builder(context, ID_LOW_BATTERY)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_battery_alert_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .setContentIntent(pendingIntentForLowBattery)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(22, notification);

    }
}