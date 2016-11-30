package com.jongmin.mylifelogger;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;

/**
 * Created by JongMin on 2016-11-23.
 */
public class TaskActivity extends AppCompatActivity implements CommonData {

    TaskOpenHelper taskHelper = new TaskOpenHelper(this);
    SQLiteDatabase taskDB;

    TextView optText;

    public void taskSQLexec(String name, Date date, Double latitude, Double longitude, String task, SQLiteDatabase db) {
        db.execSQL("insert into member(name, date, latitude, longitude, task) values ("
                + name + ", "
                + date + ", "
                + latitude + ", "
                + longitude + ", "
                + task +");"
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        optText = (TextView) findViewById(R.id.textView12);

        Button studyButton = (Button) findViewById(R.id.stdbtn);
        Button classButton = (Button) findViewById(R.id.clsbtn);
        Button hobbyButton = (Button) findViewById(R.id.chibtn);
        Button friendButton = (Button) findViewById(R.id.frdbtn);
        Button gameButton = (Button) findViewById(R.id.gambtn);
        Button walkButton = (Button) findViewById(R.id.wakbtn);
        Button startEvent = (Button) findViewById(R.id.startevt);
        Button endEvent = (Button) findViewById(R.id.endevt);

        studyButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                optText.setText("공부를 선택하셨습니다.");
            }
        });

        classButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                optText.setText("강의를 선택하셨습니다.");
            }
        });

        hobbyButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                optText.setText("취미를 선택하셨습니다.");
            }
        });

        friendButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                optText.setText("친목을 선택하셨습니다.");
            }
        });

        gameButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                optText.setText("게임을 선택하셨습니다.");
            }
        });

        walkButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                optText.setText("걷기를 선택하셨습니다.");
            }
        });

        startEvent.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (evtcnt.get(0) == 0)
                {
                    evtcnt.add(0,1);
                    finish();
                }
                else
                {
                    // 한번에 두개 이벤트를 추가 못하도록 알람 팝업
                    AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
                    builder.setMessage("한번에 두개 작업을 추가할 수 없습니다. 진행중인 작업을 먼저 종료하세요.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // None
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

        endEvent.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (evtcnt.get(0) == 1)
                {
                    evtcnt.add(0,0);
                    finish();
                }
                else
                {
                    // 이벤트 시작 안하고 종료 못하도록 알람 팝업
                    AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
                    builder.setMessage("새로운 작업을 시작하지 않은 상태에서 작업을 종료할 수 없습니다. 새로운 작업을 먼저 시작해 주세요.").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // None
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
}
