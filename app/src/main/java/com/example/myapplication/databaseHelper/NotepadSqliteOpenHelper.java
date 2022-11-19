package com.example.myapplication.databaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotepadSqliteOpenHelper extends SQLiteOpenHelper {
    public static final String NOTE_DB_NAME = "noteSQLite.db";
    public static final String NOTE_TABLE_NAME = "note";
    public static final String createtablesql = "create table if not exists"
            + NOTE_TABLE_NAME +
            "(id integer primary key autoincrement, title text, content text, create_time date, create_date date)";

    public NotepadSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null) {
            db.execSQL(createtablesql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
