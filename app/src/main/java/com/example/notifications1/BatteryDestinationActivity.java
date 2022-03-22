package com.example.notifications1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BatteryDestinationActivity extends AppCompatActivity {
    TextView chargingTitle, chargingDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_destination);

        chargingTitle = findViewById(R.id.chargingTitle);
        chargingDesc = findViewById(R.id.chargingDesc);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("message");

        chargingTitle.setText(title);
        chargingDesc.setText(desc);
    }
}