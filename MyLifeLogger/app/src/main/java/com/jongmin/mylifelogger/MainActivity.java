package com.jongmin.mylifelogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.activity_main);

        final Intent TaskIntent = new Intent(this, TaskActivity.class);
        final Intent MokpyoIntent = new Intent(this, MokpyoActivity.class);
        final Intent StatIntent = new Intent(this, StatActivity.class);

        Button TaskButton = (Button) findViewById(R.id.taskbtn);
        Button MokpyoButton = (Button) findViewById(R.id.mokpyobtn);
        Button StatButton = (Button) findViewById(R.id.statbtn);

        TaskButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(TaskIntent);
            }
        });

        MokpyoButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(MokpyoIntent);
            }
        });

        StatButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(StatIntent);
            }
        });
    }
}
