package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityTestBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestActivity extends AppCompatActivity {

//    public static final String ACCOUNT = "witer";
//    public static final String PASSWORD = "wtl020503";
    private TextView tv_readtest;
    private ExecutorService executorService;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding inflate ;
        inflate = ActivityTestBinding.inflate(getLayoutInflater());
        tv_readtest = inflate.tvReadtest;
        setContentView(inflate.getRoot());
        executorService = Executors.newSingleThreadExecutor();

        // 开始异步任务
//        fetchApiResponse();
    }

}
