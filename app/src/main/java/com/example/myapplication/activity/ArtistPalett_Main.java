package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FragmentIndexAdapter;
import com.example.myapplication.fragment.ArtistPalettCollection_Fragment;
import com.example.myapplication.fragment.ArtistPalettDrawboard_Fragment;
import com.example.myapplication.fragment.ArtistPalettPicture_Fragment;
import com.example.myapplication.fragment.ArtistPalettSetting_Fragment;
import com.example.myapplication.view.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;

public class ArtistPalett_Main extends AppCompatActivity {

    private FragmentIndexAdapter ArtistPalett_fragmentAdapter;
    private ImageView iv_index_bottom_bar_picList,
            iv_index_bottom_bar_palett,
            iv_index_bottom_bar_collection,
            iv_index_bottom_bar_setting;
    private MyViewPager index_vp_fragment_list_top;
    private LinearLayout index_bottom_bar_picList,
            index_bottom_bar_palett,
            index_bottom_bar_collection,
            index_bottom_bar_setting;
    private RelativeLayout index_rl_contain;
    private List<Fragment> mfragment;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_palett_main2);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        initView();
        initData();
        initEvent();
    }
    private void initEvent() {
        index_bottom_bar_picList.setOnClickListener(new TabOnClickListener(0));
        index_bottom_bar_palett.setOnClickListener(new TabOnClickListener(1));
        index_bottom_bar_collection.setOnClickListener(new TabOnClickListener(2));
        index_bottom_bar_setting.setOnClickListener(new TabOnClickListener(3));
    }
    private void initData() {
        mfragment = new ArrayList<Fragment>();
        mfragment.add(ArtistPalettPicture_Fragment.newInstance(getResources().getString(R.string.index_bottom_bar_picList)));
        mfragment.add(ArtistPalettDrawboard_Fragment.newInstance(getResources().getString(R.string.index_bottom_bar_palett)));
        mfragment.add(ArtistPalettCollection_Fragment.newInstance(getResources().getString(R.string.index_bottom_bar_collection)));
        mfragment.add(ArtistPalettSetting_Fragment.newInstance(getResources().getString(R.string.index_bottom_bar_setting)));
        initIndexFragmentAdapter();
    }

    private void initIndexFragmentAdapter() {
        ArtistPalett_fragmentAdapter = new FragmentIndexAdapter(this.getSupportFragmentManager(), mfragment);
        index_vp_fragment_list_top.setAdapter(ArtistPalett_fragmentAdapter);
        iv_index_bottom_bar_picList.setSelected(true);
        index_vp_fragment_list_top.setCurrentItem(3);
        index_vp_fragment_list_top.setOffscreenPageLimit(3);
        index_vp_fragment_list_top.addOnPageChangeListener(new TabOnPageChangeListener());
    }

    /**
     * Bottom_Bar的点击事件
     */
    public class TabOnClickListener implements View.OnClickListener {

        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            if (index == 4) {
                // 跳转到Scan界面
//                Toast.makeText(MainActivity.this, "点击了扫描按钮", Toast.LENGTH_SHORT).show();
            } else {
                //选择某一页
                index_vp_fragment_list_top.setCurrentItem(index, false);
            }
        }

    }
    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {
        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }



        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            resetTextView();
            switch (position) {
                case 0:
                    index_bottom_bar_picList.setSelected(true);
                    break;
                case 1:
                    index_bottom_bar_palett.setSelected(true);
                    break;
                case 2:
                    index_bottom_bar_collection.setSelected(true);
                    break;
                case 3:
                    index_bottom_bar_setting.setSelected(true);
                    break;
            }
            ImageChange(position);

        }
        /*
        * 选择对应的图片进行图标切换
        * */
        private void ImageChange(int drawble) {
            switch (drawble) {
                case 0:
                    iv_index_bottom_bar_picList.setImageResource(R.drawable.picture_on);
                    iv_index_bottom_bar_palett.setImageResource(R.drawable.drawing_borad_off);
                    iv_index_bottom_bar_collection.setImageResource(R.drawable.collectioff);
                    iv_index_bottom_bar_setting.setImageResource(R.drawable.setting_off);
                    break;
                case 1:
                    iv_index_bottom_bar_picList.setImageResource(R.drawable.picture_off);
                    iv_index_bottom_bar_palett.setImageResource(R.drawable.drawing_borad_on);
                    iv_index_bottom_bar_collection.setImageResource(R.drawable.collectioff);
                    iv_index_bottom_bar_setting.setImageResource(R.drawable.setting_off);
                    break;
                case 2:
                    iv_index_bottom_bar_picList.setImageResource(R.drawable.picture_off);
                    iv_index_bottom_bar_palett.setImageResource(R.drawable.drawing_borad_off);
                    iv_index_bottom_bar_collection.setImageResource(R.drawable.collection);
                    iv_index_bottom_bar_setting.setImageResource(R.drawable.setting_off);
                    break;
                case 3:
                    iv_index_bottom_bar_picList.setImageResource(R.drawable.picture_off);
                    iv_index_bottom_bar_palett.setImageResource(R.drawable.drawing_borad_off);
                    iv_index_bottom_bar_collection.setImageResource(R.drawable.collectioff);
                    iv_index_bottom_bar_setting.setImageResource(R.drawable.setting_on);
                    break;
            }
        }
    }
    private void initView() {
        //图片
        iv_index_bottom_bar_picList = findViewById(R.id.iv_index_bottom_bar_picList);
        iv_index_bottom_bar_palett = findViewById(R.id.iv_index_bottom_bar_palett);
        iv_index_bottom_bar_collection = findViewById(R.id.iv_index_bottom_bar_collection);
        iv_index_bottom_bar_setting = findViewById(R.id.iv_index_bottom_bar_setting);
        index_vp_fragment_list_top = findViewById(R.id.index_vp_fragment_list_top);
        //点击布局
        index_bottom_bar_picList = findViewById(R.id.index_bottom_bar_picList);
        index_bottom_bar_palett = findViewById(R.id.index_bottom_bar_palett);
        index_bottom_bar_collection = findViewById(R.id.index_bottom_bar_collection);
        index_bottom_bar_setting = findViewById(R.id.index_bottom_bar_setting);
        //碎片内容
        index_rl_contain = findViewById(R.id.index_rl_contain);
    }
    /**
     * 重置所有TextView的字体颜色
     */
    private void resetTextView() {
        index_bottom_bar_picList.setSelected(false);
        index_bottom_bar_palett.setSelected(false);
        index_bottom_bar_collection.setSelected(false);
        index_bottom_bar_setting.setSelected(false);
    }
}