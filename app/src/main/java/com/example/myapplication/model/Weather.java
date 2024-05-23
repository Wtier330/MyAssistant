package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
@Data
public class Weather {
    /*
    "cityid": "101210901",
  "city": "金华",
  "cityEn": "jinhua",
  "country": "中国",
  "countryEn": "China",
  "update_time": "2022-12-05 23:55:28",
     */
//    String
    @SerializedName("cityid")
    private String cityid;
    @SerializedName("city")
    private String city;
    @SerializedName("update_time")
    private String updateTime;
    @SerializedName("data")
    private List<DayWeather> dayWeathers;
}

