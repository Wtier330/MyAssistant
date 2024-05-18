package com.example.myapplication.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Builder;
import lombok.Data;

@Data
@Entity(tableName = "tb_goods")
//id	CusBarcode	GoodsName  MinUnit	PakeagRate	MaxUnit	BarCode	InPrice	SalePrice	CreateTime	ModifyTime
public class Goods {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "CusBarCode")
    private String CusBarcode;
    @ColumnInfo(name = "GoodsName")
    private String GoodsName;
    @ColumnInfo(name = "MinUnit")
    private String MinUnit;
    @ColumnInfo(name = "PakeagRate")
    private String PakeagRate;
    @ColumnInfo(name = "MaxUnit")
    private String MaxUnit;
    @ColumnInfo(name = "BarCode")
    private String BarCode;
    @ColumnInfo(name = "InPrice")
    private String InPrice;
    @ColumnInfo(name = "SalePrice")
    private String SalePrice;
    @ColumnInfo(name = "CreateTime")
    private String CreateTime;
    @ColumnInfo(name = "ModifyTime")
    private String ModifyTime;
}
