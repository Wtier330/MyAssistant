package com.example.myapplication.repository;

import static com.example.myapplication.constants.DBconstants.DB_NAME;

import android.content.Context;

import androidx.room.Room;

public class DatabaseInitializer {
    private static AppDatabase appDatabase;

    public static void initDatabase(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public static GoodsDao getGoodsDao() {
        return appDatabase.goodsDao();
    }
}
