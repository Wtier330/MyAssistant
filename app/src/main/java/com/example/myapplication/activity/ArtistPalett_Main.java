package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import lombok.SneakyThrows;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.ArtistPalettDrawboard_Fragment;
import com.example.myapplication.fragment.ArtistPalettPicture_Fragment;
import com.example.myapplication.fragment.ArtistPalettSetting_Fragment;

public class ArtistPalett_Main extends AppCompatActivity {
    private int[] settingicons = {R.drawable.setting_off,
            R.drawable.setting_on,
            R.drawable.drawing_borad_off,
            R.drawable.drawing_borad_on,
            R.drawable.picture_off,
            R.drawable.picture_on};
    private FragmentTransaction beginTransaction;
    private FrameLayout fragment_container;
    private ImageView ivApfragPicture;
    private ImageView ivApfragDrawboard;
    private ImageView ivApfragSetting;


    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_palett_main);
        Thread.sleep(20);
        fraginit();
    }

    public void showFragmentDrawboard(View view) {
        beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettDrawboard_Fragment());
        beginTransaction.commit();
    }

    public void showFragmentPicture(View view) {
        beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettPicture_Fragment());
        beginTransaction.commit();
    }

    public void showFragmentSetting(View view) {
        // 进行 Fragment 的事务操作
        beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettSetting_Fragment());
        beginTransaction.commit();

    }

    private void fraginit() {
        //开启事务
        beginTransaction = getFragmentManager().beginTransaction();
        //碎片界面的第一个页面
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettDrawboard_Fragment());
        //提交
        beginTransaction.commit();
        //初始化载入界面
        fragment_container = findViewById(R.id.fragment_container);
        ivApfragPicture = (ImageView) findViewById(R.id.iv_apfrag_picture);
        ivApfragDrawboard = (ImageView) findViewById(R.id.iv_apfrag_drawboard);
        ivApfragSetting = (ImageView) findViewById(R.id.iv_apfrag_setting);

//        ivApfragPicture.setOnClickListener(this::showFragmentDrawboard);
        ivApfragPicture.setOnClickListener(this::showFragmentDrawboard);
        ivApfragDrawboard.setOnClickListener(this::showFragmentPicture);
        ivApfragSetting.setOnClickListener(this::showFragmentSetting);
    }
}