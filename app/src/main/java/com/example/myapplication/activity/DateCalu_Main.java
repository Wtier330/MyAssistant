package com.example.myapplication.activity;

import static com.example.myapplication.utils.TimeUtil.isValidDate;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.bean.DateCalu;
import com.example.myapplication.database.DateCaluSqliteHelper;
import com.example.myapplication.utils.EditTextUtil;
import com.example.myapplication.utils.ToastUtil;
import com.example.myapplication.view.MyEditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author witer330
 * @date 2024/04/13
 * 日期计算器住界面
 */
public class DateCalu_Main extends AppCompatActivity {
    private TextView tv_DateCalu_RegionTitle;
    private TextView tv_DateCalue_Result;
    private MyEditText et_DateCalu_InputYear;
    private MyEditText et_DateCalu_InputMonth;
    private MyEditText et_DateCalu_InputDay;
    private MyEditText et_DateCalu_Input;
    private int input = (int) (Math.random() * 100);
    private String year, month, day;
    private String outyear, outmonth, outday;
    private Button mBtDateCaluBc;
    private Button mBtDateCaluAd;
    private MyEditText mEtDateCaluOutputYear, mEtDateCaluOutputMonth, mEtDateCaluOutputDay;
    private Button mBtDateCaluDiff;
    private TextView mTvDateCalueDiff;
    private LocalDate date, outdate;
    private ImageView iv_Calu_clipBoard, iv_Calu_Collection;
    private ClipboardManager clipboardManager;
    private DateCalu dateCalu;
    private List<DateCalu> dateCaluList = new ArrayList<>();
    private DateCaluSqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_calu_main);
        initDB();
        initView();
        initData();
        initEvent();
    }

    private void initDB() {
        sqliteHelper = new DateCaluSqliteHelper(this);

    }

    private void initData() {
        et_DateCalu_Input.setText(String.valueOf(input));
        LocalDate currentDate = LocalDate.now();
        year = String.valueOf(currentDate.getYear());
        month = String.valueOf(currentDate.getMonthValue());
        day = String.valueOf(currentDate.getDayOfMonth());

        et_DateCalu_InputYear.setText(year);
        et_DateCalu_InputMonth.setText(month);
        et_DateCalu_InputDay.setText(day);

    }


    @SuppressLint("SetTextI18n")
    private void initEvent() {
        mBtDateCaluAd.setOnClickListener((l) -> {
            year = et_DateCalu_InputYear.getTextToStr();
            month = et_DateCalu_InputMonth.getTextToStr();
            day = et_DateCalu_InputDay.getTextToStr();
            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !et_DateCalu_Input.isNull()) {
                et_DateCalu_InputYear.InputNotEmpty();
                et_DateCalu_InputMonth.InputNotEmpty();
                et_DateCalu_InputDay.InputNotEmpty();
                et_DateCalu_Input.InputNotEmpty();
                if (et_DateCalu_InputYear.validate()) {
                    input = Integer.parseInt(et_DateCalu_Input.getTextToStr());
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
                booInteraction(et_DateCalu_InputYear);
                booInteraction(et_DateCalu_InputMonth);
                booInteraction(et_DateCalu_InputDay);
            }
            if (et_DateCalu_Input.isNull()) {
                EditTextUtil.InputIsEmpty(this, et_DateCalu_Input);
                input = 0;
            }
        });

        mBtDateCaluBc.setOnClickListener((l) -> {
            year = et_DateCalu_InputYear.getTextToStr();
            month = et_DateCalu_InputMonth.getTextToStr();
            day = et_DateCalu_InputDay.getTextToStr();

            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !et_DateCalu_Input.isNull()) {
                et_DateCalu_InputYear.InputNotEmpty();
                et_DateCalu_InputMonth.InputNotEmpty();
                et_DateCalu_InputDay.InputNotEmpty();
                et_DateCalu_Input.InputNotEmpty();
                if (et_DateCalu_InputYear.validate()) {
                    input = Integer.parseInt(et_DateCalu_Input.getTextToStr());
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
                booInteraction(et_DateCalu_InputYear);
                booInteraction(et_DateCalu_InputMonth);
                booInteraction(et_DateCalu_InputDay);
            }
            if (et_DateCalu_Input.isNull()) {
                EditTextUtil.InputIsEmpty(this, et_DateCalu_Input);
                input = 0;
            }
        });

        mBtDateCaluDiff.setOnClickListener((l) -> {
            outyear = mEtDateCaluOutputYear.getTextToStr();
            outmonth = mEtDateCaluOutputMonth.getTextToStr();
            outday = mEtDateCaluOutputDay.getTextToStr();
            year = et_DateCalu_InputYear.getTextToStr();
            month = et_DateCalu_InputMonth.getTextToStr();
            day = et_DateCalu_InputDay.getTextToStr();
            if (!year.isEmpty() && !month.isEmpty() && !day.isEmpty() && !outyear.isEmpty() && !outmonth.isEmpty() && !outday.isEmpty()) {
                et_DateCalu_InputYear.InputNotEmpty();
                et_DateCalu_InputMonth.InputNotEmpty();
                et_DateCalu_InputDay.InputNotEmpty();
                mEtDateCaluOutputYear.InputNotEmpty();
                mEtDateCaluOutputMonth.InputNotEmpty();
                mEtDateCaluOutputDay.InputNotEmpty();
                if (et_DateCalu_InputYear.validate() && mEtDateCaluOutputYear.validate()) {
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
                booInteraction(et_DateCalu_InputYear);
                booInteraction(et_DateCalu_InputMonth);
                booInteraction(et_DateCalu_InputDay);
                booInteraction(mEtDateCaluOutputYear);
                booInteraction(mEtDateCaluOutputMonth);
                booInteraction(mEtDateCaluOutputDay);
            }
        });
        iv_Calu_clipBoard.setOnClickListener((l) -> {
            clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("label", tv_DateCalue_Result.getText());
            clipboardManager.setPrimaryClip(clipData);
            ToastUtil.toastShort(this, "以下信息被复制到剪切板\n" + tv_DateCalue_Result.getText());
        });
        iv_Calu_Collection.setOnClickListener((l) -> {
            MyEditText myEditText = new MyEditText(this) ;
            myEditText.setSingleLine();
            myEditText.setHint("标签");
            myEditText.requestFocus();
            myEditText.setFocusable(true);
            AlertDialog.Builder inputDialog = new AlertDialog.Builder(this)
                    .setTitle("设置一个备注~")
                    .setView(myEditText)
                    .setPositiveButton("确定",(dialog,which)->{
                        dateCalu.setName(myEditText.getTextToStr());
                        dateCalu.setResult(tv_DateCalue_Result.getText().toString().trim());
//                        dateCalu.setInputTime();
//                        sqliteHelper.insertData();
                    }).setPositiveButton("取消",(dialog,i)->{
                       dialog.dismiss();
                    });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
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

    private void booInteraction(MyEditText editText) {
        if (!editText.isNull()) {
            editText.InputNotEmpty();
        } else {
            editText.InputIsEmpty();
        }
    }

    private void initView() {
        et_DateCalu_InputYear = findViewById(R.id.et_DateCalu_InputYear);
        et_DateCalu_InputYear.setValidFun((value) -> {
            return value.length() == 4;
        });
        et_DateCalu_InputMonth = findViewById(R.id.et_DateCalu_InputMonth);
        et_DateCalu_InputDay = findViewById(R.id.et_DateCalu_InputDay);
        et_DateCalu_Input = findViewById(R.id.et_DateCalu_Input);
        tv_DateCalue_Result = findViewById(R.id.tv_DateCalue_Result);
        tv_DateCalu_RegionTitle = findViewById(R.id.tv_DateCalu_RegionTitle);
        mBtDateCaluBc = findViewById(R.id.bt_DateCalu_bc);
        mBtDateCaluAd = findViewById(R.id.bt_DateCalu_ad);
        mEtDateCaluOutputYear = findViewById(R.id.et_DateCalu_OutputYear);
        mEtDateCaluOutputYear.setValidFun((value) -> {
            return value.length() == 4;
        });
        mEtDateCaluOutputMonth = findViewById(R.id.et_DateCalu_OutputMonth);
        mEtDateCaluOutputDay = findViewById(R.id.et_DateCalu_OutputDay);
        mBtDateCaluDiff = findViewById(R.id.bt_DateCalu_diff);
        mTvDateCalueDiff = findViewById(R.id.tv_DateCalue_diff);
        iv_Calu_clipBoard = findViewById(R.id.iv_Calu_clipBoard);
        iv_Calu_Collection = findViewById(R.id.iv_Calu_Collection);
    }

}