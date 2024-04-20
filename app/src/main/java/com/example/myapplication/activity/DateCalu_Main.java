package com.example.myapplication.activity;

import static com.example.myapplication.utils.TimeUtil.isValidDate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.utils.EditTextUtils;
import com.example.myapplication.utils.ToastUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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
    private int input = 367;
    private String year, month, day;
    private String outyear, outmonth, outday;
    private Button mBtDateCaluBc;
    private Button mBtDateCaluAd;
    private EditText mEtDateCaluOutputYear;
    private EditText mEtDateCaluOutputMonth;
    private EditText mEtDateCaluOutputDay;
    private Button mBtDateCaluDiff;
    private TextView mTvDateCalueDiff;
    private LocalDate date, outdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_calu_main);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        LocalDate currentDate = LocalDate.now();
        input = Integer.parseInt(Objects.requireNonNull(et_DateCalu_Input.getText()).toString());
        year = String.valueOf(currentDate.getYear());
        month = String.valueOf(currentDate.getMonthValue());
        day = String.valueOf(currentDate.getDayOfMonth());

        et_DateCalu_InputYear.setText(String.valueOf(currentDate.getYear()));
        et_DateCalu_InputMonth.setText(String.valueOf(currentDate.getMonthValue()));
        et_DateCalu_InputDay.setText(String.valueOf(currentDate.getDayOfMonth()));
    }


    @SuppressLint("SetTextI18n")
    private void initEvent() {
        mBtDateCaluAd.setOnClickListener((l) -> {
            year = et_DateCalu_InputYear.getText().toString().trim();
            month = et_DateCalu_InputMonth.getText().toString().trim();
            day = et_DateCalu_InputDay.getText().toString().trim();
            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !et_DateCalu_Input.getText().toString().trim().isEmpty()) {
                EditTextUtils.InputNotEmpty(this, et_DateCalu_InputYear, et_DateCalu_InputMonth, et_DateCalu_InputDay, et_DateCalu_Input);
                if (year.length() == 4) {
                    input = Integer.parseInt(et_DateCalu_Input.getText().toString());
                    et_DateCalu_Input.setText(String.valueOf(Math.abs(input)));
                    if (isValidDate(year, month, day)) {
                        date = formateDate(year, month, day);
                        tv_DateCalue_Result.setText(addOrSubtractDays(formateDate(year, month, day), Math.abs(input)).toString());
                    } else {
                        ToastUtil.toastShort(this, "输入的日期不合理");
                    }
                } else {
                    ToastUtil.toastShort(this, "仅支持四位数年份");

                }

            } else {
                booInteraction(year, et_DateCalu_InputYear);
                booInteraction(month, et_DateCalu_InputMonth);
                booInteraction(day, et_DateCalu_InputDay);
            }
            if (et_DateCalu_Input.getText().toString().trim().isEmpty()) {
                EditTextUtils.InputIsEmpty(this, et_DateCalu_Input);
                input = 0;
            }
        });

        mBtDateCaluBc.setOnClickListener((l) -> {
            year = et_DateCalu_InputYear.getText().toString().trim();
            month = et_DateCalu_InputMonth.getText().toString().trim();
            day = et_DateCalu_InputDay.getText().toString().trim();

            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !et_DateCalu_Input.getText().toString().trim().isEmpty()) {
                EditTextUtils.InputNotEmpty(this, et_DateCalu_InputYear, et_DateCalu_InputMonth, et_DateCalu_InputDay, et_DateCalu_Input);
                if (year.length() == 4) {
                    input = Integer.parseInt(et_DateCalu_Input.getText().toString());
                    et_DateCalu_Input.setText(String.valueOf(-Math.abs(input)));
                    if (isValidDate(year, month, day)) {
                        date = formateDate(year, month, day);
                        tv_DateCalue_Result.setText(addOrSubtractDays(formateDate(year, month, day), -Math.abs(input)).toString());
                    } else {
                        ToastUtil.toastShort(this, "输入的日期不合理");
                    }
                } else {
                    ToastUtil.toastShort(this, "仅支持四位数年份");
                }

            } else {
                booInteraction(year, et_DateCalu_InputYear);
                booInteraction(month, et_DateCalu_InputMonth);
                booInteraction(day, et_DateCalu_InputDay);
            }
            if (et_DateCalu_Input.getText().toString().trim().isEmpty()) {
                EditTextUtils.InputIsEmpty(this, et_DateCalu_Input);
                input = 0;
            }

        });
        mBtDateCaluDiff.setOnClickListener((l) -> {
            outyear = mEtDateCaluOutputYear.getText().toString().trim();
            outmonth = mEtDateCaluOutputMonth.getText().toString().trim();
            outday = mEtDateCaluOutputDay.getText().toString().trim();
            year = et_DateCalu_InputYear.getText().toString().trim();
            month = et_DateCalu_InputMonth.getText().toString().trim();
            day = et_DateCalu_InputDay.getText().toString().trim();
            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !outyear.isEmpty() && !outmonth.isEmpty() && !outday.isEmpty()) {
                EditTextUtils.InputNotEmpty(this, et_DateCalu_InputDay, et_DateCalu_InputMonth, et_DateCalu_InputYear, mEtDateCaluOutputYear, mEtDateCaluOutputMonth, mEtDateCaluOutputDay);
                if (year.length() == 4 && outyear.length() == 4) {
                    if (isValidDate(year, month, day) && isValidDate(outyear, outmonth, outday)) {
                        date = formateDate(year, month, day);
                        outdate = formateDate(outyear, outmonth, outday);

                        mTvDateCalueDiff.setText(String.valueOf(calculateDaysBetween(outdate, date)));

                    } else {
                        ToastUtil.toastShort(this, "输入的日期不合理");
                    }
                } else {
                    ToastUtil.toastShort(this, "仅支持四位数年份");

                }

            } else {
                booInteraction(year, et_DateCalu_InputYear);
                booInteraction(month, et_DateCalu_InputMonth);
                booInteraction(day, et_DateCalu_InputDay);
                booInteraction(outyear, mEtDateCaluOutputYear);
                booInteraction(outmonth, mEtDateCaluOutputMonth);
                booInteraction(outday, mEtDateCaluOutputDay);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    //判断是否为闰年
    private boolean isLeapYear(int input) {
        return (input % 400 == 0) || (input % 4 == 0 && input % 100 != 0);
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


    private LocalDate formateDate(String y, String m, String d) {
        if (Integer.parseInt(m) < 10) {
            m = "0".concat(m);
        }
        if (Integer.parseInt(d) < 10) {
            d = "0".concat(d);
        }
        return parseDate(y.concat("-").concat(m).concat("-").concat(d));
    }

    private void booInteraction(String Date, EditText editText) {
        if (!TextUtils.isEmpty(Date)) {
            EditTextUtils.InputNotEmpty(this, editText);
        } else {
            EditTextUtils.InputIsEmpty(this, editText);
        }
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

}