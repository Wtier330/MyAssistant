package com.example.myapplication.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.bean.Goods;
@Database(entities = {Goods.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GoodsDao goodsDao();
}
