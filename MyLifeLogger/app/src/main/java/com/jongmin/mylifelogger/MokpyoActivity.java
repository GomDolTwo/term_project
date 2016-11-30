package com.jongmin.mylifelogger;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by JongMin on 2016-11-23.
 */
public class MokpyoActivity extends AppCompatActivity {

    MokpyoOpenHelper mokpyoHelper = new MokpyoOpenHelper(this);
    SQLiteDatabase mokpyoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mokpyo);
    }
}
