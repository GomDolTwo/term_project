package com.jongmin.mylifelogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JongMin on 2016-11-30.
 */

public class MokpyoOpenHelper extends SQLiteOpenHelper {

    Context context;
    public MokpyoOpenHelper(Context context) {
        super(context, "mokpyo_test.sqlite", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE member (name TEXT, startDate NUMERIC, dueDate NUMERIC, mokpyo TEXT, type INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE member (name TEXT, startDate NUMERIC, dueDate NUMERIC, mokpyo TEXT, type INTEGER);");
    }

}
