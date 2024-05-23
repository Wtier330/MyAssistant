package com.example.myapplication.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.Goods;
@Database(entities = {Goods.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GoodsDao goodsDao();
}
