package com.jongmin.mylifelogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JongMin on 2016-11-30.
 */

public class TaskOpenHelper extends SQLiteOpenHelper {

    Context context;
    public TaskOpenHelper(Context context) {
        super(context, "task_test_v3.sqlite", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE member (name TEXT, year INTEGER, month INTEGER, day INTEGER, hour INTEGER, min INTEGER, sec INTEGER, latitude DOUBLE, longitude DOUBLE, start INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE member (name TEXT, year INTEGER, month INTEGER, day INTEGER, hour INTEGER, min INTEGER, sec INTEGER, latitude DOUBLE, longitude DOUBLE, start INTEGER);");
    }
}
