package com.example.myapplication.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "tb_goods")
//id	CusBarcode	GoodsName  MinUnit	PakeagRate	MaxUnit	BarCode	InPrice	SalePrice	CreateTime	ModifyTime
@Data
public class Goods {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "CusBarCode")
    public String CusBarcode;
    @ColumnInfo(name = "GoodsName")
    public String GoodsName;
    @ColumnInfo(name = "MinUnit")
    public String MinUnit;
    @ColumnInfo(name = "PakeagRate")
    public String PakeagRate;
    @ColumnInfo(name = "MaxUnit")
    public String MaxUnit;
    @ColumnInfo(name = "BarCode")
    public String BarCode;
    @ColumnInfo(name = "InPrice")
    public String InPrice;
    @ColumnInfo(name = "SalePrice")
    public String SalePrice;
    @ColumnInfo(name = "CreateTime")
    public String CreateTime;
    @ColumnInfo(name = "ModifyTime")
    public String ModifyTime;

}
