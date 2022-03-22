package com.example.notifications1;

import static com.example.notifications1.NotificationChannels.ID_DEVICE_IDLE;
import static com.example.notifications1.NotificationChannels.ID_NET_CHANGED;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (networkInfo != null){
            Intent intentForIdleNet = new Intent(context, NetworkDestinationActivity.class);
            intentForIdleNet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntentForNet = PendingIntent.getActivity(context, 62, intentForIdleNet, 0);

            String title = "Network State: " + networkInfo.getTypeName() ;
            String message = String.valueOf(wifiManager.getConnectionInfo());
            Notification notification  = new NotificationCompat.Builder(context, ID_NET_CHANGED)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_baseline_network_check_24)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_SERVICE)
                    .setContentIntent(pendingIntentForNet)
                    .build();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(12, notification);
        }
    }
}