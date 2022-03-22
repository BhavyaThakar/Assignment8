package com.example.notifications1;

import static com.example.notifications1.NotificationChannels.ID_DEVICE_CHARGING;
import static com.example.notifications1.NotificationChannels.ID_GPS_ENABLED;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class GpsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            Intent intentForGps = new Intent(context, GpsDestinationActivity.class);
            intentForGps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentForGps = PendingIntent.getActivity(context, 45, intentForGps, PendingIntent.FLAG_UPDATE_CURRENT);

            String title = "GPS";
            String message = "GPS is enabled";
            Notification notification  = new NotificationCompat.Builder(context, ID_DEVICE_CHARGING)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_baseline_gps_fixed_24)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                    .setContentIntent(pendingIntentForGps)
                    .setAutoCancel(true)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(45, notification);
        }
        else {

            Intent intentForGps = new Intent(context, GpsDestinationActivity.class);
            intentForGps.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentForGps = PendingIntent.getActivity(context, 44, intentForGps, 0);

            String title = "GPS";
            String message = "GPS is disabled";
            Notification notification  = new NotificationCompat.Builder(context, ID_GPS_ENABLED)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_baseline_gps_fixed_24)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                    .setContentIntent(pendingIntentForGps)
                    .setAutoCancel(true)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(44, notification);

        }

    }
}
