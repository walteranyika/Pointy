package com.walter.pointend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = getSharedPreferences("settings",MODE_PRIVATE);
        final  boolean status =prefs.getBoolean("status",false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (status)
                    startActivity(new Intent(SplashActivity.this, DestinationActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
}
