package com.davidargote.apppruebarepo2.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.davidargote.apppruebarepo2.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
                
            }
        };

        timer.schedule(task, 3000);

    }
}
