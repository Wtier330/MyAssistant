package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_COLLECTION;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.myapplication.R;
import com.example.myapplication.utils.TimeUtil;

import java.util.Calendar;
import java.util.Date;

public class ArtistPalettCollection_Fragment extends Fragment {

    private TextView tv_artistpalett_collection_startTime;
    private TextView tv_artistpalett_collection_count;
    private TextView tv_artistpalett_collection_endTime;
    private Spinner sp_artistpalett_collection_timeChoice;
    private RecyclerView rl_artistpalett_collection_list;
    private ImageView iv_artistpalett_collection_search;
    private EditText et_artistpalett_collection_search;

    private TimePickerView pvTime; //时间选择器对象
    private String[] searchDays;
    private ArrayAdapter<String> sparrayAdapter;
    private String selectedDays;
    private LinearLayout ll_artist_contant  ;
    private ScrollView sv_artist_contant;

    public static ArtistPalettCollection_Fragment newInstance(String sectionNumber) {
        ArtistPalettCollection_Fragment fragment = new ArtistPalettCollection_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_COLLECTION, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artistpalett_collection_fragment, container, false);
        initview(view);
        initEvent();
        return view;
    }

    private void initEvent() {
//        tv_artistpalett_drawboard_startTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initTimePicker(tv_artistpalett_drawboard_startTime);
//                pvTime.show();
//            }
//        });

//        tv_artistpalett_drawboard_endTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initTimePicker(tv_artistpalett_drawboard_endTime);
//                pvTime.show();
//            }
//        });
//        sp_artistpalett_drawboard_timeChoice.setBackgroundColor(Color.argb(50, 255, 255, 255));
//        searchDays = getResources().getStringArray(R.array.searchDay);
//
//        sparrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.sp_wearther_item_layout, searchDays);
//        sp_artistpalett_drawboard_timeChoice.setAdapter(sparrayAdapter);
//        sp_artistpalett_drawboard_timeChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedDays = searchDays[position];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
    }

    private void initview(View view) {


    }

    //初始化时间选择器
    private void initTimePicker(TextView tv_date) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1, 0, 0);//起始时间
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 12, 31, 23, 59);//结束时间
        pvTime = new TimePickerView.Builder(getActivity(),
                new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        //选中事件回调
                        //mTvMyBirthday 这个组件就是个TextView用来显示日期 如2020-09-08
//                        mTvMyBirthday.setText(getTimes(date));
                        tv_date.setText(TimeUtil.getTimes(date));
                    }
                })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, true, false, false})
                .setLabel("年", "月", "日", "时", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(15)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .setCancelText("取消")
                .setSubmitText("确定")
                .setCancelColor(R.color.teal_700)
                .build();
    }
}
