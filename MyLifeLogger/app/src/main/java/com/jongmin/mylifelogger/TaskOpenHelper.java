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
        super(context, "task_test.sqlite", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE member (name TEXT, date NUMERIC, latitude DOUBLE, longitude DOUBLE, task TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE member (name TEXT, date NUMERIC, latitude DOUBLE, longitude DOUBLE, task TEXT);");
    }
}
