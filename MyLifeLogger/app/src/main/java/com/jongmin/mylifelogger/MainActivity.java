package com.jongmin.mylifelogger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements CommonData, SensorEventListener {

    public static GoogleMap mGoogleMap;

    double currentlat, currentlon;
    LatLng currentloc;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    int count = 0;
    int dir_UP = 0;
    int dir_DOWN = 0;
    double acceleration = 0;
    double gravity = 9.8;
    float x = 0f;
    float y = 0f;
    float z = 0f;

    ImageView iv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 로딩화면이 빨리 사라지는것을 방지
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        evtcnt.add(0,0);    // 이벤트 등록 여부

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // 핸드폰의 GPS 사용 유무를 확인하여 GPS가 꺼져있을 시 켜게끔 유도
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("GPS를 켜야 어플이 작동됩니다. GPS를 켜고 어플을 재시작 해주시기 바랍니다.").setCancelable(false).setPositiveButton("GPS 켜기", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent gpsOptionIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(gpsOptionIntent);
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        init();

        final Intent TaskIntent = new Intent(this, TaskActivity.class);
        final Intent MokpyoIntent = new Intent(this, MokpyoActivity.class);
        final Intent StatIntent = new Intent(this, StatActivity.class);

        Button TaskButton = (Button) findViewById(R.id.taskbtn);
        Button MokpyoButton = (Button) findViewById(R.id.mokpyobtn);
        Button StatButton = (Button) findViewById(R.id.statbtn);
        Button CameraButton = (Button) findViewById(R.id.cambtn);

        TaskButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                GpsInfo gps = new GpsInfo(MainActivity.this);

                if (gps.isGetLocation()) {
                    currentlat = gps.getLatitude();
                    currentlon = gps.getLongitude();
                }

                latitude.add(0, currentlat);
                longitude.add(0, currentlon);

                startActivity(TaskIntent);
            }
        });

        MokpyoButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(MokpyoIntent);
            }
        });

        StatButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                manbo.add(0, count);
                startActivity(StatIntent);
            }
        });

        CameraButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(intent, 1);
                } catch (NullPointerException e) {
                    // 사진 안찍었을 때
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            iv.setImageURI(data.getData());
        } catch (NullPointerException e) {
            // 사진 안찍었을 때
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 아무 동작 안함
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            acceleration = Math.sqrt(x*x+y*y+z*z);
        }

        if (acceleration - gravity > 5) {
            dir_UP = 1;
        }

        if (dir_UP == 1 && gravity - acceleration > 5) {
            dir_DOWN = 1;
        }

        if (dir_DOWN == 1) {
            count++;

            dir_UP = 0;
            dir_DOWN = 0;
        }
    }

    public void init() {
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        mGoogleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        GpsInfo gps = new GpsInfo(this);

        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            currentlat = gps.getLatitude();
            currentlon = gps.getLongitude();

            // LatLng 값 등록
            currentloc = new LatLng(currentlat, currentlon);

            // LatLng 값 등록된 것 기반으로 현재위치 출력
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentloc));

            // Map 을 zoom
            mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(13));

            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(currentloc);// 위도 • 경도
            optFirst.title("Current Position");// 제목 미리보기
            optFirst.icon(BitmapDescriptorFactory.fromResource(R.mipmap.current_location)); // 아이콘
            mGoogleMap.addMarker(optFirst).showInfoWindow();
        }
    }
}
