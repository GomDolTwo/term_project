package com.jongmin.mylifelogger;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    Cursor rs;
    String a = "";

    TextView manbocount;
    TextView studyCount;
    TextView classCount;
    TextView hobbyCount;
    TextView friendCount;
    TextView gameCount;
    TextView walkCount;

    int std, cls, hby, fri, gam, wak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        std = cls = hby = fri = gam = wak = 0;
        taskDB = taskHelper.getWritableDatabase();

        final Intent chartIntent = new Intent(this, ChartActivity.class);

        manbocount = (TextView)findViewById(R.id.textView11);

        studyCount = (TextView)findViewById(R.id.textView19);
        classCount = (TextView)findViewById(R.id.textView20);
        hobbyCount = (TextView)findViewById(R.id.textView21);
        friendCount = (TextView)findViewById(R.id.textView22);
        gameCount = (TextView)findViewById(R.id.textView23);
        walkCount = (TextView)findViewById(R.id.textView24);

        manbocount.setText(Integer.toString(manbo.get(0)));

        rs = taskDB.rawQuery("select * from member;", null);
        while (rs.moveToNext()) {
            a = rs.getString(0);
            switch (a) {
                case "study" :
                    ++std;
                    break;
                case "class" :
                    ++cls;
                    break;
                case "hobby" :
                    ++hby;
                    break;
                case "friend" :
                    ++fri;
                    break;
                case "game" :
                    ++gam;
                    break;
                case "walk" :
                    ++wak;
                    break;
                default :
                    break;
            }
        }

        studyCount.setText(Integer.toString(std));
        classCount.setText(Integer.toString(cls));
        hobbyCount.setText(Integer.toString(hby));
        friendCount.setText(Integer.toString(fri));
        gameCount.setText(Integer.toString(gam));
        walkCount.setText(Integer.toString(wak));

        Button goalButton = (Button) findViewById(R.id.goalbtn);
        Button graphButton = (Button) findViewById(R.id.graphbtn);

        goalButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

            }
        });

        graphButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(chartIntent);
            }
        });
    }
}
