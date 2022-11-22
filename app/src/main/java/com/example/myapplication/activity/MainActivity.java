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
        CardView card_lab = findViewById(R.id.card_lab);
        CardView card_lab_swipe = findViewById(R.id.card_lab_swipe);
        SetOnClickListener(bt_notepad);
        SetOnClickListener(card_lab);
        SetOnClickListener(card_lab_swipe);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_notepad:
                startActivity(new Intent(getApplicationContext(), Notepad_Main.class));
                break;
            case R.id.card_lab:
                new MaterialAlertDialogBuilder(this)
                        .setTitle("标题")
                        .setMessage("描述信息")
                        .setIcon(R.drawable.add_icon)
                        .setNeutralButton("取消", (dialog, which) -> {
                            // Respond to neutral button press
                            Toast.makeText(this, "<<< 取消 >>>", Toast.LENGTH_SHORT);
                        })
                        .setNegativeButton("拒绝", (dialog, which) -> {
                            // Respond to negative button press
                            Toast.makeText(this, "<<< 拒绝 >>>", Toast.LENGTH_SHORT);
                        })
                        .setPositiveButton("同意", (dialog, which) -> {
                            // Respond to positive button press
                            Toast.makeText(this, "<<< 同意 >>>", Toast.LENGTH_SHORT);
                        })
                        .show();
                break;
            case R.id.card_lab_swipe:
                startActivity(new Intent(getApplicationContext(), NoteListActivity.class));
                break;
        }
    }
}