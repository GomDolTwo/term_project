package com.jongmin.mylifelogger;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by JongMin on 2016-12-03.
 */

public class ChartActivity extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        lineChart = (LineChart) findViewById(R.id.chart);

        ArrayList<String> xAXES = new ArrayList<>();
        ArrayList<Entry> yAXESsin = new ArrayList<>();
        ArrayList<Entry> yAXEScos = new ArrayList<>();

        double x = 0;
        int numDataPoints = 6;

        for(int i=0; i<numDataPoints; i++){
            float sinFunction = Float.parseFloat(String.valueOf(Math.sin(x)));
            float cosFunction = Float.parseFloat(String.valueOf(Math.cos(x)));
            x = x + 0.1;
            yAXESsin.add(new Entry(sinFunction,i));
            yAXEScos.add(new Entry(cosFunction,i));
            xAXES.add(i, String.valueOf(x));   //x축의 값을 저장합니다.
        }

        String[] xaxes = new String[xAXES.size()];
        for(int i=0; i<xAXES.size(); i++){
            xaxes[i] = xAXES.get(i).toString();   // 아래그림의 동그란 부분에 표시되는 x축 값.
        }

        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();

        //아래 그림의 파란색 그래프
        LineDataSet lineDataSet1 = new LineDataSet(yAXEScos, "cos");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setColor(Color.BLUE);

        //아래 그림의 빨간색 그래프
        LineDataSet lineDataSet2 = new LineDataSet(yAXESsin, "sin");
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(lineDataSet2));
        lineChart.setVisibleXRangeMaximum(65f);

        lineChart.animateXY(2000, 2000);
    }
}
