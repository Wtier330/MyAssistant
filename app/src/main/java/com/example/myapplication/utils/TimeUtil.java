package com.example.myapplication.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TimeUtil {
    protected String getCreateTimeAsString(String parttern) {
        String s = new SimpleDateFormat(parttern).toString();
        return s.format(String.valueOf(new Date()));
    }

    public String getCreateTimeAsString() {
        return getCreateTimeAsString("HH:mm");
    }

    public String getCreateDateAsString() {
        return getCreateTimeAsString("YYYY-MM-dd");
    }

    //格式化时间
    public static String getTimes(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        return format.format(date);
    }

    public static boolean isValidDate(int year, int month, int dayOfMonth) {
        try {
            // 使用 LocalDate 类创建日期对象
            LocalDate date = LocalDate.of(year, month, dayOfMonth);
            return true;
        } catch (Exception e) {
            // 如果日期无效，会抛出异常，捕获异常并返回 false
            return false;
        }
    }

    public static boolean isValidDate(String year, String month, String dayOfMonth) {
        try {
            // 使用 LocalDate 类创建日期对象
            LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(dayOfMonth));
            return true;
        } catch (Exception e) {
            // 如果日期无效，会抛出异常，捕获异常并返回 false
            return false;
        }
    }
}
