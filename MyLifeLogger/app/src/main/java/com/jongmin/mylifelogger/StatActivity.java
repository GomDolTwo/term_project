package com.jongmin.mylifelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by JongMin on 2016-11-23.
 */
public class StatActivity extends AppCompatActivity implements CommonData {

    TextView manbocount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        manbocount = (TextView)findViewById(R.id.textView11);

        manbocount.setText(Integer.toString(manbo.get(0)));
    }
}
