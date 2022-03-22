package com.example.notifications1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Switch chargingNotiSw, lowBatterySw, locationSw, idleDeviceSw, netSw;
    PendingIntent pendingIntentForCharging, pendingIntentForLowBattery, pendingIntentForAlarm, pendingIntentForLocation;
    ChargingReceiver chargingReceiver = new ChargingReceiver();
    LowBatteryReceiver lowBatteryReceiver = new LowBatteryReceiver();
    private MaterialTimePicker picker;
    private Calendar calendar;
    TextView showTime;
    private AlarmManager alarmManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        turnOnOffAlarm();
        showTime = findViewById(R.id.show_time_alarm_tv);
        chargingNotiSw = findViewById(R.id.charging_switch);
        chargingNotiSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    registerForBroadcastsOfCharging(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, ChargingReceiver.class);
                    pendingIntentForCharging = PendingIntent.getBroadcast(MainActivity.this, 12 , intent, 0);
                    sendBroadcast(intent);
                }
                else {
                    disableBroadcastOfCharging(MainActivity.this);
                }
            }
        });

        lowBatterySw = findViewById(R.id.switch_lb);
        lowBatterySw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    registerForBroadcastsOfLowBattery(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, LowBatteryReceiver.class);
                    pendingIntentForLowBattery = PendingIntent.getBroadcast(MainActivity.this, 22, intent, 0);
                    sendBroadcast(intent);

                }else {
                    disableBroadcastOfLowBattery(MainActivity.this);
                }

            }
        });


        locationSw = findViewById(R.id.switch_gps);
        locationSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intentFosGpsService = new Intent(getBaseContext(), GpsServices.class);

                if (isChecked){
                    getBaseContext().startService(intentFosGpsService);
                    Intent intent = new Intent(MainActivity.this, GpsReceiver.class);
                    pendingIntentForLocation = PendingIntent.getBroadcast(MainActivity.this, 45, intent, 0);
                    sendBroadcast(intent);

                    }else {
                    getBaseContext().stopService(intentFosGpsService);
                }

                }

        });

        idleDeviceSw = findViewById(R.id.switch_idle_device);
        idleDeviceSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent idleDeviceService = new Intent(getBaseContext(),IdleDeviceServices.class);
                if (isChecked){
                    getBaseContext().startService(idleDeviceService);
                }else{
                    getBaseContext().stopService(idleDeviceService);
                }
            }
        });

        netSw = findViewById(R.id.switch_net_changed);
        netSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intentForNetService  = new Intent(getBaseContext(), NetworkReceiver.class);
                if (isChecked){
                    getBaseContext().startService(intentForNetService);

                }else{
                    getBaseContext().stopService(intentForNetService);
                }
            }
        });









    }

    private void disableBroadcastOfCharging(Context context) {
        context.unregisterReceiver(chargingReceiver);
    }

    public void registerForBroadcastsOfCharging(Context context) {
        IntentFilter charging = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        context.registerReceiver(chargingReceiver, charging);
    }

    private void disableBroadcastOfLowBattery(Context context) {
        context.unregisterReceiver(lowBatteryReceiver);
    }

    public void registerForBroadcastsOfLowBattery(Context context) {
        IntentFilter charging = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        context.registerReceiver(lowBatteryReceiver, charging);
    }

    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getSupportFragmentManager(), "alarm");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picker.getHour() > 12){
                    showTime.setText(
                            String.format("%02d", (picker.getHour() -12))+ " :" + String.format("%02d", picker.getMinute())+ " PM");
                }else {
                    showTime.setText(picker.getHour() + " : " + picker.getHour() + " AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND,0);
            }
        });
    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntentForAlarm = PendingIntent.getBroadcast(this,0 , intent, 0 );
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntentForAlarm);
        Toast.makeText(this, "Alarm set successfully", Toast.LENGTH_SHORT).show();
    }

    private void cancelAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntentForAlarm = PendingIntent.getBroadcast(this, 0 , intent, 0);

        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntentForAlarm);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannelForAlarm() {
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            CharSequence name = "Alarm";
            String description = "Channel for alarm manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("ALARM_CHANNEL", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void turnOnOffAlarm(){

        createNotificationChannelForAlarm();
        ImageView setAlarm = findViewById(R.id.image_set_alarm);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        Switch setAlarmButton = findViewById(R.id.set_alarm_button);
        setAlarmButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setAlarm();

                }
                else{
                    cancelAlarm();
                }
            }
        });

    }







}