package com.example.myapplication.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    public static final String ACCOUNT = "witer";
    public static final String PASSWORD = "wtl020503";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding inflate ;
        inflate = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(inflate.getRoot());
        // 假设你已经得到了图片的URL

        String imageUrl = "https://s2.loli.net/2024/05/19/mGHBJ9U7bItS54j.png";
        ImageView imageView = inflate.ivReadIcon;
        inflate.btReadIcon.setOnClickListener((v -> {
            // 使用Glide加载图片
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);
        }));
    }
}