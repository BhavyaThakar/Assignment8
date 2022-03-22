package com.example.notifications1;

import static com.example.notifications1.NotificationChannels.ID_DEVICE_CHARGING;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ChargingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentForCharging = new Intent(context, BatteryDestinationActivity.class);
        intentForCharging.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntentForCharging = PendingIntent.getActivity(context, 12, intentForCharging, 0);

        String title = "Charging";
        String message = "Device is being charged";
        intentForCharging.putExtra("title", title);
        intentForCharging.putExtra("message", message);
        Notification notification  = new NotificationCompat.Builder(context, ID_DEVICE_CHARGING)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_battery_charging_full_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .setContentIntent(pendingIntentForCharging)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(12, notification);





    }
}