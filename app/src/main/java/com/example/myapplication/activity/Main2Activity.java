package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFullscreen(true, true);
        setAndroidNativeLightStatusBar(this, true);
        viewinit();
    }

    //设置监听
    private void SetOnClickListener(View button) {
        button.setOnClickListener(this);
    }

    //控件初始化
    private void viewinit() {
        FloatingActionButton fbtMainSetting = (FloatingActionButton) findViewById(R.id.fbt_main_setting);
        CardView bt_notepad = findViewById(R.id.bt_notepad);
        CardView bt_weather = findViewById(R.id.bt_weather);
        CardView bt_weeklyreport = findViewById(R.id.bt_weeklyreport);
        CardView bt_color = findViewById(R.id.bt_color);
        CardView bt_hotComments = findViewById(R.id.bt_hotComments);
        CardView bt_DateCalu = findViewById(R.id.bt_DateCalu);
        CardView bt_test = findViewById(R.id.bt_test);

        SetOnClickListener(bt_DateCalu);
        SetOnClickListener(bt_test);
        SetOnClickListener(bt_weather);
        SetOnClickListener(bt_notepad);
        SetOnClickListener(bt_weeklyreport);
        SetOnClickListener(bt_color);
        SetOnClickListener(bt_hotComments);

    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notepad://笔记
                startActivity(new Intent(getApplicationContext(), Notepad_Main.class));
                break;
            case R.id.bt_weather://天气
                startActivity(new Intent(getApplicationContext(), Weather_Main.class));
                break;
            case R.id.bt_weeklyreport://周报生成器
                startActivity(new Intent(getApplicationContext(), WeeklyReport_Main.class));
                break;
            case R.id.bt_color://调色盘
                startActivity(new Intent(getApplicationContext(), ArtistPalett_Main.class));
                break;
            case R.id.bt_hotComments://网易热评
                startActivity(new Intent(getApplicationContext(), HotCom_Main.class));
                break;
            case R.id.bt_DateCalu://日期计算器
                startActivity(new Intent(getApplicationContext(), DateCalu_Main.class));
                break;
            case R.id.bt_test://日期计算器
                startActivity(new Intent(getApplicationContext(), TestActivity.class));
                break;

        }
    }
    //TODO 设置悬浮按钮，对页面进行设置

    /*
     * 设置按钮操作事件
     * */
    public void settingMain(View view) {
        String[] str = getResources().getStringArray(R.array.function);
        AlertDialog alertDialog = new AlertDialog.Builder(this)

//        final View view = factory.inflate(R.layout.city, null);//这里必须是final的

                .setTitle("选择功能")
                .setIcon(R.drawable.tools)
                .setPositiveButton("ok", null)
//                .setView()
                .show();
//       //TODO dialog的复选框修改
        //dialog 点击空白区域默认退出
        getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    alertDialog.dismiss();

                }
                return true;
            }
        });

    }

    public void setFullscreen(boolean isShowStatusBar, boolean isShowNavigationBar) {
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        if (!isShowStatusBar) {
            uiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (!isShowNavigationBar) {
            uiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);

        //隐藏标题栏
        // getSupportActionBar().hide();
        setNavigationStatusColor(Color.TRANSPARENT);
    }

    public void setNavigationStatusColor(int color) {
        //VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
        }
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

}