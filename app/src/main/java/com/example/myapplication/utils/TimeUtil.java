package com.example.myapplication.utils;

import java.text.SimpleDateFormat;
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
}
