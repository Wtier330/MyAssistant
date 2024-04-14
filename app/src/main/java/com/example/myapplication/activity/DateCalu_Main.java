package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author witer330
 * @date 2024/04/13
 * 日期计算器住界面
 */
public class DateCalu_Main extends AppCompatActivity {
    private TextView tv_DateCalu_RegionTitle;
    private TextView tv_DateCalue_Result;
    private EditText et_DateCalu_InputYear;
    private EditText et_DateCalu_InputMonth;
    private EditText et_DateCalu_InputDay;
    private EditText et_DateCalu_Input;
    private boolean LeapYear = false;
    private int input = 367;

    private String year, month, day;
    private Button mBtDateCaluBc;
    private Button mBtDateCaluAd;
    private EditText mEtDateCaluOutputYear;
    private EditText mEtDateCaluOutputMonth;
    private EditText mEtDateCaluOutputDay;
    private Button mBtDateCaluDiff;
    private TextView mTvDateCalueDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_calu_main);
        initView();
        eventView();
    }

    @SuppressLint("SetTextI18n")
    private void eventView() {
        mBtDateCaluAd.setOnClickListener((l) -> {
            input = Integer.parseInt(et_DateCalu_Input.getText().toString());
            year = et_DateCalu_InputYear.getText().toString().trim();
            month = et_DateCalu_InputMonth.getText().toString().trim();
            day = et_DateCalu_InputDay.getText().toString().trim();
            Dateformate(month, day);
            // 获取当前日期
            String date = year + "-" + month + "-" + day;
            tv_DateCalue_Result.setText(addOrSubtractDays(parseDate(date), input).toString());
        });
    }

    private void initView() {
        et_DateCalu_InputYear = findViewById(R.id.et_DateCalu_InputYear);
        et_DateCalu_InputMonth = findViewById(R.id.et_DateCalu_InputMonth);
        et_DateCalu_InputDay = findViewById(R.id.et_DateCalu_InputDay);
        et_DateCalu_Input = findViewById(R.id.et_DateCalu_Input);
        tv_DateCalue_Result = findViewById(R.id.tv_DateCalue_Result);
        tv_DateCalu_RegionTitle = findViewById(R.id.tv_DateCalu_RegionTitle);
        mBtDateCaluBc = findViewById(R.id.bt_DateCalu_bc);
        mBtDateCaluAd = findViewById(R.id.bt_DateCalu_ad);
        mEtDateCaluOutputYear = findViewById(R.id.et_DateCalu_OutputYear);
        mEtDateCaluOutputMonth = findViewById(R.id.et_DateCalu_OutputMonth);
        mEtDateCaluOutputDay = findViewById(R.id.et_DateCalu_OutputDay);
        mBtDateCaluDiff = findViewById(R.id.bt_DateCalu_diff);
        mTvDateCalueDiff = findViewById(R.id.tv_DateCalue_diff);
    }

    //判断是否为闰年
//    private boolean isLeapYear(Object input) {
//        int put = 0;
//        if (input instanceof String) {
//             put = Integer.parseInt((String) input);
//        } else if (input instanceof Integer) {
//             put = (Integer) input;
//        }
//        LeapYear = (put % 400 == 0) || (put % 4 == 0 && put % 100 != 0);
//        return LeapYear;
//
//    }
    private boolean isLeapYear(int input) {
        return LeapYear = (input % 400 == 0) || (input % 4 == 0 && input % 100 != 0);

    }
    // 解析日期字符串为 LocalDate 对象
    private static LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

    // 计算两个日期之间的天数差距
    private static long calculateDaysBetween(LocalDate date1, LocalDate date2) {
        return Math.abs(ChronoUnit.DAYS.between(date1, date2));
    }

    // 在指定日期上添加或减去指定天数
    private static LocalDate addOrSubtractDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    private void Dateformate(String month, String day) {

        if (Integer.parseInt(month) > 10) {
            this.month = "0".concat(month);
        }
        if (Integer.parseInt(day) >10) {
            this.day = "0".concat(day);
        }
    }
}