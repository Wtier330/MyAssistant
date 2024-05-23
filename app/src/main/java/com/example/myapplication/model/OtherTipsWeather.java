package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class OtherTipsWeather {
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
