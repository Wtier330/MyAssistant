package com.example.myapplication.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ArtistPalett implements Serializable {
    private int id;
    private String color;
    private String colorTag;
    private Date createTime;

}
