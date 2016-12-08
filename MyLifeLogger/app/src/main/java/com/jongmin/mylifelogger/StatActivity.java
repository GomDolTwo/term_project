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

    TextView studyTimer;
    TextView classTimer;
    TextView hobbyTimer;
    TextView friendTimer;
    TextView gameTimer;
    TextView walkTimer;

    int std, cls, hby, fri, gam, wak;
    int std_start=0, cls_start=0, hby_start=0, fri_start=0, gam_start=0, wak_start=0;

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

        studyTimer = (TextView)findViewById(R.id.textView25);
        classTimer = (TextView)findViewById(R.id.textView26);
        hobbyTimer = (TextView)findViewById(R.id.textView27);
        friendTimer = (TextView)findViewById(R.id.textView28);
        gameTimer = (TextView)findViewById(R.id.textView29);
        walkTimer = (TextView)findViewById(R.id.textView30);

        manbocount.setText(Integer.toString(manbo.get(0)));

        rs = taskDB.rawQuery("select * from member;", null);
        while (rs.moveToNext()) {
            a = rs.getString(0);
            switch (a) {
                case "study" :
                    ++std;
                    std_start += rs.getInt(9);
                    break;
                case "class" :
                    ++cls;
                    cls_start += rs.getInt(9);
                    break;
                case "hobby" :
                    ++hby;
                    hby_start += rs.getInt(9);
                    break;
                case "friend" :
                    ++fri;
                    fri_start += rs.getInt(9);
                    break;
                case "game" :
                    ++gam;
                    gam_start += rs.getInt(9);
                    break;
                case "walk" :
                    ++wak;
                    wak_start += rs.getInt(9);
                    break;
                default :
                    break;
            }

            studyTimer.setText(timerCalc(std_start));
            classTimer.setText(timerCalc(cls_start));
            hobbyTimer.setText(timerCalc(hby_start));
            friendTimer.setText(timerCalc(fri_start));
            gameTimer.setText(timerCalc(gam_start));
            walkTimer.setText(timerCalc(wak_start));
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

    public String timerCalc(int start) {
        int hour, min, sec;

        if (start > 60*60)
        {
            hour = start / (60*60);
            start = start - hour*60*60;
            min = start / 60;
            sec = start - min*60;
        }
        else if (start > 60)
        {
            hour = 0;
            min = start / 60;
            sec = start - min*60;
        }
        else
        {
            hour = 0;
            min = 0;
            sec = start;
        }

        return Integer.toString(hour) + "시간 " + Integer.toString(min) + "분 " + Integer.toString(sec) + "초";
    }
}
