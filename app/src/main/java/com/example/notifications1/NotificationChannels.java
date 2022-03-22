package com.example.notifications1;


import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

public class NotificationChannels extends Application {
    public static final String ID_DEVICE_CHARGING = "Channel for charging";
    public static final String ID_LOW_BATTERY = "Channel for low battery";
    public static final String ID_NET_CHANGED = "Channel for network state changed";
    public static final String ID_GPS_ENABLED = "Channel for GPS enabled / disabled";
    public static final String ID_DEVICE_IDLE = "Channel for idle device";
    public static final String ID_ALARM = "Channel for alarm";

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            NotificationChannel channelForChargingNotification = new NotificationChannel(
                    ID_DEVICE_CHARGING, "Channel for charging",
                    NotificationManager.IMPORTANCE_HIGH);
            channelForChargingNotification.setDescription("this is Channel for charging notification");

            NotificationChannel channelForLowBatteryNotification = new NotificationChannel(
                    ID_LOW_BATTERY, "Channel for low battery",
                    NotificationManager.IMPORTANCE_HIGH);
            channelForLowBatteryNotification.setDescription("this is Channel for low battery notification");

            NotificationChannel channelForNetstateChanged = new NotificationChannel(
                    ID_NET_CHANGED, "Channel for change in network state",
                    NotificationManager.IMPORTANCE_HIGH);
            channelForNetstateChanged.setDescription("this is Channel for change in network state");

            NotificationChannel channelForGPSEnabled = new NotificationChannel(
                    ID_GPS_ENABLED, "Channel for GPS notifications",
                    NotificationManager.IMPORTANCE_HIGH);
            channelForGPSEnabled.setDescription("this is Channel for gps notification");

            NotificationChannel channelForIdleDevice = new NotificationChannel(
                    ID_DEVICE_IDLE, "Channel for Idle device notification ",
                    NotificationManager.IMPORTANCE_HIGH);
            channelForIdleDevice.setDescription("this is Channel for idle device notifications");



            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channelForChargingNotification);
            manager.createNotificationChannel(channelForLowBatteryNotification);
            manager.createNotificationChannel(channelForNetstateChanged);
            manager.createNotificationChannel(channelForGPSEnabled);
            manager.createNotificationChannel(channelForIdleDevice);


        }
    }
}
