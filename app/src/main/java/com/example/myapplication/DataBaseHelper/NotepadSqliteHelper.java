package com.example.myapplication.DataBaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotepadSqliteHelper extends SQLiteOpenHelper {

    public NotepadSqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null) {
            db.execSQL("create table if not exists" +
                    " notepadTable(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "insert_time data," +
                    "title varchar," +
                    "content text )");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
