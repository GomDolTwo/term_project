package com.jongmin.mylifelogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by JongMin on 2016-11-23.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
