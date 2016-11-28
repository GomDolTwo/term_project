package com.jongmin.mylifelogger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    protected GoogleMap mGoogleMap;

    double currentlat, currentlon;
    LatLng currentloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SplashActivity.class));
        setContentView(R.layout.activity_main);

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

        init();

        final Intent TaskIntent = new Intent(this, TaskActivity.class);
        final Intent MokpyoIntent = new Intent(this, MokpyoActivity.class);
        final Intent StatIntent = new Intent(this, StatActivity.class);

        Button TaskButton = (Button) findViewById(R.id.taskbtn);
        Button MokpyoButton = (Button) findViewById(R.id.mokpyobtn);
        Button StatButton = (Button) findViewById(R.id.statbtn);

        TaskButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
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
                startActivity(StatIntent);
            }
        });
    }

    public void init() {
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

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

            /* 현재 위치 마커 설정
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(currentloc);// 위도 • 경도
            optFirst.title("Current Position");// 제목 미리보기
            optFirst.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)); // 아이콘
            mGoogleMap.addMarker(optFirst).showInfoWindow();
            */
        }
    }
}
