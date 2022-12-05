package com.example.myapplication.bean;

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

@Data
class DayWeather {
    @SerializedName("day")
    private String day;
    @SerializedName("date")
    private String date;
    @SerializedName("week")
    private String week;
    @SerializedName("wea")
    private String wea;
    @SerializedName("wea_img")
    private String weaImg;
    @SerializedName("wea_day")
    private String weaDay;
    @SerializedName("tem")
    private String tem;
    @SerializedName("tem1")
    private String tem1;
    @SerializedName("tem2")
    private String tem2;
    @SerializedName("win")
    private String[] win;
    @SerializedName("win_speed")
    private String winSpeed;
    @SerializedName("sunrise")
    private String sunrise;
    @SerializedName("sunset")
    private String sunset;
    @SerializedName("air")
    private String air;
    @SerializedName("air_level")
    private String airLevel;
    @SerializedName("air_tips")
    private String airTips;
    @SerializedName("index")
    private List<OtherTipsWeather> otherTipsWeathers;

     /*
     *[
        {
            "day":"05日（星期一）",
            "date":"2022-12-05",
            "week":"星期一",
            "wea":"小雨转阴",
            "wea_img":"yu",
            "wea_day":"小雨",
            "wea_day_img":"yu",
            "wea_night":"阴",
            "wea_night_img":"yin",
            "tem":"6℃",
            "tem1":"7℃",
            "tem2":"6℃",
            "humidity":"84%",
            "visibility":"10km",
            "pressure":"1022",
            "win":Array[2],
            "win_speed":"<3级",
            "win_meter":"1km/h",
            "sunrise":"06:40",
            "sunset":"17:03",
            "air":"42",
            "air_level":"优",
            "air_tips":"空气很好，可以外出活动，呼吸新鲜空气，拥抱大自然！",
     * */
}
@Data
class OtherTipsWeather {
    /*
    "title":"紫外线指数",
    "level":"最弱",
     "desc":"辐射弱，涂擦SPF8-12防晒护肤品。"
                },
    */
    @SerializedName("title")
    private String title;
    @SerializedName("level")
    private String level;
    @SerializedName("desc")
    private String desc;
}