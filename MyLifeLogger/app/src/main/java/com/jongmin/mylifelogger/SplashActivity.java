package com.jongmin.mylifelogger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by JongMin on 2016-11-23.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler() {
            public void handleMessage(Message msg) {
                finish();
            }
        };

        hd.sendEmptyMessageDelayed(0, 2000);
    }
}
