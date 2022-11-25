package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
        SetOnClickListener(bt_notepad);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notepad:
                startActivity(new Intent(getApplicationContext(), Notepad_Main.class));
                break;
        }
    }
}