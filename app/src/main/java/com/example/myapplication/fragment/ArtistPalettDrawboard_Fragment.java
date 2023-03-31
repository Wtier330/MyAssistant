package com.example.myapplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArtistPalettDrawboard_Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private View view;
    private TextView tvApColorR;
    private TextView tvApColorG;
    private TextView tvApColorB;
    private View apView;
    private FloatingActionButton fbt_apdf_lockColor;
    private SeekBar sbApR;
    private SeekBar sbApG;
    private SeekBar sbApB;
    private String hex = "#646464";
    private String hexR = "64";
    private String hexG = "64";
    private String hexB = "64";
    private int[] rgb = {100, 100, 100};
    private TextView tv_ap_hex;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artistpalett_drawboard_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        tv_ap_hex = view.findViewById(R.id.tv_ap_hex);
        fbt_apdf_lockColor = view.findViewById(R.id.fbt_apdf_lockColor);
        tvApColorR = (TextView) view.findViewById(R.id.tv_ap_colorR);
        tvApColorG = (TextView) view.findViewById(R.id.tv_ap_colorG);
        tvApColorB = (TextView) view.findViewById(R.id.tv_ap_colorB);
        apView = (View) view.findViewById(R.id.ap_view);
        sbApR = (SeekBar) view.findViewById(R.id.sb_ap_R);
        sbApG = (SeekBar) view.findViewById(R.id.sb_ap_G);
        sbApB = (SeekBar) view.findViewById(R.id.sb_ap_B);
        fbt_apdf_lockColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 屏幕rgb锁定
                 * */
            }
        });
        sbApR.setOnSeekBarChangeListener(this);
        sbApG.setOnSeekBarChangeListener(this);
        sbApB.setOnSeekBarChangeListener(this);
    }

    //將10進制轉換為16進制
    public static String encodeHEX(Integer numb) {

        String hex = Integer.toHexString(numb);
        return hex;

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb_ap_R:
                rgb[0] = progress;
                tvApColorR.setText(String.valueOf(progress));
                hexR = encodeHEX(progress);
                break;
            case R.id.sb_ap_G:
                rgb[1] = progress;
                tvApColorG.setText(String.valueOf(progress));
                hexG = encodeHEX(progress);
                break;
            case R.id.sb_ap_B:
                rgb[2] = progress;
                tvApColorB.setText(String.valueOf(progress));
                hexB = encodeHEX(progress);
                break;
        }
        hex = String.valueOf("#"+hexR+hexG+hexB);
        tv_ap_hex.setText(hex);
        apView.setBackgroundColor(Color.rgb(rgb[0],rgb[1],rgb[2]));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.sb_ap_R:
                break;
            case R.id.sb_ap_G:
                break;
            case R.id.sb_ap_B:
                break;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.sb_ap_R:
                break;
            case R.id.sb_ap_G:
                break;
            case R.id.sb_ap_B:
                break;
        }
    }
}
