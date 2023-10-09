package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_DRAW;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.utils.ToastUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

@SuppressLint("NonConstantResourceId")
public class ArtistPalettDrawboard_Fragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private View view;
    private TextView tvApColorR;
    private TextView tvApColorG;
    private TextView tvApColorB;
    private View apView;
    private FloatingActionButton fbt_apdf_lockColor,
            fbt_apdf_starColor;
    private SeekBar sbApR;
    private SeekBar sbApG;
    private SeekBar sbApB;
    private String hex = "#646464";
    private String hexR = "64";
    private String hexG = "64";
    private String hexB = "64";
    private int[] rgb = {100, 100, 100};
    private TextView tv_ap_hex;
    private ClipboardManager clipboardManager;

    public static androidx.fragment.app.Fragment newInstance(String sectionNumber) {
        ArtistPalettDrawboard_Fragment fragment = new ArtistPalettDrawboard_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_DRAW, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artistpalett_drawboard_fragment, container, false);
        initView();

        return view;
    }

    private void initView() {
        tv_ap_hex = view.findViewById(R.id.tv_ap_hex);
        tvApColorR = (TextView) view.findViewById(R.id.tv_ap_colorR);
        tvApColorG = (TextView) view.findViewById(R.id.tv_ap_colorG);
        tvApColorB = (TextView) view.findViewById(R.id.tv_ap_colorB);
        apView = (View) view.findViewById(R.id.ap_view);
        sbApR = (SeekBar) view.findViewById(R.id.sb_ap_R);
        sbApG = (SeekBar) view.findViewById(R.id.sb_ap_G);
        sbApB = (SeekBar) view.findViewById(R.id.sb_ap_B);
        sbApR.setOnSeekBarChangeListener(this);
        sbApG.setOnSeekBarChangeListener(this);
        sbApB.setOnSeekBarChangeListener(this);

        fbt_apdf_lockColor = view.findViewById(R.id.fbt_apdf_lockColor);
        clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        fbt_apdf_lockColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 屏幕rgb锁定
                 * */
                String text = tv_ap_hex.getText().toString().trim();
                ClipData clipData = ClipData.newPlainText("label", text);
                clipboardManager.setPrimaryClip(clipData);
                ToastUtil.toastShort(getActivity(), String.valueOf(text + "已经复制到剪切板"));
            }
        });
        /*
         * 颜色收藏
         * */

        fbt_apdf_starColor = view.findViewById(R.id.fbt_apdf_starColor);

        fbt_apdf_starColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 点击进行收藏，收藏内容在Collection中展示
                ToastUtil.toastShort(getActivity(), "收藏成功");
                fbt_apdf_starColor.setBackgroundColor(R.drawable.staron);
                fbt_apdf_starColor.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.green8CC7B5)));
                fbt_apdf_starColor.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.khaki)));
            }
        });
    }

    //10进制转16进制并大写
    public static String encodeHEX(Integer numb) {

        String hex = Integer.toHexString(numb);
        return hex.toUpperCase();

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
        hex = String.valueOf("#" + hexR + hexG + hexB);
        tv_ap_hex.setText(hex);
        //TODO 将手动调试最后的颜色进行收藏比对，如果收藏中存在此颜色，则自动切换图标状态，表示已收藏
        apView.setBackgroundColor(Color.rgb(rgb[0], rgb[1], rgb[2]));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
