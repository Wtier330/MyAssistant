package com.example.myapplication.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.fragment.ArtistPalettDrawboard_Fragment;
import com.example.myapplication.fragment.ArtistPalettPicture_Fragment;
import com.example.myapplication.fragment.ArtistPalettSetting_Fragment;

import androidx.appcompat.app.AppCompatActivity;
import lombok.SneakyThrows;

public class ArtistPalett_Main extends AppCompatActivity {
    private int[] settingicons = {R.drawable.setting_off,
            R.drawable.setting_on,
            R.drawable.drawing_borad_off,
            R.drawable.drawing_borad_on,
            R.drawable.picture_off,
            R.drawable.picture_on};
    private FragmentTransaction start;
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
        FragIconChoice(R.drawable.drawing_borad_on);

        FragmentTransaction beginTransaction;
        beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettDrawboard_Fragment());
        beginTransaction.commit();
    }

    public void showFragmentPicture(View view) {
        FragIconChoice(R.drawable.picture_on);
        FragmentTransaction beginTransaction;
        beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettPicture_Fragment());
        beginTransaction.commit();
    }

    public void showFragmentSetting(View view) {
        FragIconChoice(R.drawable.setting_on);

        // 进行 Fragment 的事务操作
        FragmentTransaction beginTransaction;
        beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, new ArtistPalettSetting_Fragment());
        beginTransaction.commit();

    }

    private void fraginit() {
        //开启事务
        start = getFragmentManager().beginTransaction();
        //碎片界面的第一个页面
        start.replace(R.id.fragment_container, new ArtistPalettDrawboard_Fragment());
        //提交
        start.commit();
//        FragIconChoice(R.drawable.drawing_borad_on);

        //初始化载入界面
        fragment_container = findViewById(R.id.fragment_container);
        ivApfragPicture = (ImageView) findViewById(R.id.iv_apfrag_picture);
        ivApfragDrawboard = (ImageView) findViewById(R.id.iv_apfrag_drawboard);
        ivApfragSetting = (ImageView) findViewById(R.id.iv_apfrag_setting);

        ivApfragPicture.setOnClickListener(this::showFragmentPicture);
        ivApfragDrawboard.setOnClickListener(this::showFragmentDrawboard);
        ivApfragSetting.setOnClickListener(this::showFragmentSetting);
        FragIconChoice(R.drawable.drawing_borad_on);

    }
/*
* 选中碎片后的图标切换
* */
    private void FragIconChoice(int iv) {
        if (iv == (settingicons[1])) {
            ivApfragPicture.setImageResource(R.drawable.picture_off);
            ivApfragDrawboard.setImageResource(R.drawable.drawing_borad_off);
            ivApfragSetting.setImageResource(R.drawable.setting_on);
        } else if (iv == (settingicons[3])) {
            ivApfragPicture.setImageResource(R.drawable.picture_off);
            ivApfragDrawboard.setImageResource(R.drawable.drawing_borad_on);
            ivApfragSetting.setImageResource(R.drawable.setting_off);
        } else if (iv == (settingicons[5])) {
            ivApfragPicture.setImageResource(R.drawable.picture_on);
            ivApfragDrawboard.setImageResource(R.drawable.drawing_borad_off);
            ivApfragSetting.setImageResource(R.drawable.setting_off);
        }
    }
}