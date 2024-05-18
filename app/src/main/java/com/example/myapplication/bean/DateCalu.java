package com.example.myapplication.bean;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DateCalu {
    private int id;
    private String name;
    private Date createTime;
    private Date inputTime;
    private String result;
    private int inputValue;
    private Date updateTime;
}
