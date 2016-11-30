package com.jongmin.mylifelogger;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.sql.Date;

/**
 * Created by JongMin on 2016-11-23.
 */
public class StatActivity extends AppCompatActivity implements CommonData {

    TaskOpenHelper taskHelper = new TaskOpenHelper(this);
    MokpyoOpenHelper mokpyoHelper = new MokpyoOpenHelper(this);
    SQLiteDatabase taskDB;
    SQLiteDatabase mokpyoDB;

    public void taskSQLexec(String name, Date date, Double latitude, Double longitude, String task, SQLiteDatabase db) {
        db.execSQL("insert into member(name, date, latitude, longitude, task) values ("
                + name + ", "
                + date + ", "
                + latitude + ", "
                + longitude + ", "
                + task +");"
        );
    }

    public void mokpyoSQLexec(String name, Date startDate, Date dueDate, String mokpyo, int type, SQLiteDatabase db) {
        db.execSQL("insert into member(name, startDate, dueDate, mokpyo, type) values ("
                + name + ", "
                + startDate + ", "
                + dueDate + ", "
                + mokpyo + ", "
                + type +");"
        );
    }

    TextView manbocount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        manbocount = (TextView)findViewById(R.id.textView11);

        manbocount.setText(Integer.toString(manbo.get(0)));
    }
}
