package com.example.notifications1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AlarmDestinationActivity extends AppCompatActivity {

    TextView alarmTitle, alarmDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_destination);

        alarmTitle =findViewById(R.id.alarmTitle);
        alarmDesc = findViewById(R.id.alarmDesc);

        String title = getIntent().getStringExtra("TITLE");
        String desc = getIntent().getStringExtra("MESSAGE");

        alarmTitle.setText(title);
        alarmDesc.setText(desc);

    }
}