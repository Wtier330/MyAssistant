package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewinit();
    }

    //设置监听
    private void SetOnClickListener(View button) {
        button.setOnClickListener(this);
    }

    //控件初始化
    private void viewinit() {
        CardView bt_notepad = findViewById(R.id.bt_notepad);
        CardView bt_weather = findViewById(R.id.bt_weather);
        CardView bt_weeklyreport= findViewById(R.id.bt_weeklyreport);

        SetOnClickListener(bt_weather);
        SetOnClickListener(bt_notepad);
        SetOnClickListener(bt_weeklyreport);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notepad:
                startActivity(new Intent(getApplicationContext(), Notepad_Main.class));
                break;
            case R.id.bt_weather:
                startActivity(new Intent(getApplicationContext(), Weather_Main.class));
                break;
            case R.id.bt_weeklyreport:
                startActivity(new Intent(getApplicationContext(), WeeklyReport.class));
                break;
        }
    }
}