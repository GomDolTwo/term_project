package com.jongmin.mylifelogger;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.sql.Date;

/**
 * Created by JongMin on 2016-11-23.
 */
public class MokpyoActivity extends AppCompatActivity {

    MokpyoOpenHelper mokpyoHelper = new MokpyoOpenHelper(this);
    SQLiteDatabase mokpyoDB;

    public void mokpyoSQLexec(String name, Date startDate, Date dueDate, String mokpyo, int type, SQLiteDatabase db) {
        db.execSQL("insert into member(name, startDate, dueDate, mokpyo, type) values ("
                + name + ", "
                + startDate + ", "
                + dueDate + ", "
                + mokpyo + ", "
                + type +");"
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mokpyo);
    }
}
