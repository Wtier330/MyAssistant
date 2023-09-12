package com.example.myapplication.database;

import static com.example.myapplication.constants.DBconstants.ARTIST_PALETT_TABLE_NAME;
import static com.example.myapplication.constants.DBconstants.DB_NAME;
import static com.example.myapplication.constants.DBconstants.NOTE_TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.bean.ArtistPalett;
import com.example.myapplication.bean.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * id: 表主键
 * color: 颜色值
 * colorTag: 颜色标识 - 可为空
 * create_time: 创建时间 (时间戳)
 */
public class ArtistPalettSqilteHelper extends SQLiteOpenHelper {
    public static final String createtablesql = "CREATE TABLE IF NOT EXISTS "
            + ARTIST_PALETT_TABLE_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " color TEXT NOT NULL,"
            + " colorTag TEXT,"
            + " create_time INTEGER)";

    public ArtistPalettSqilteHelper(@Nullable Context context ) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /**
     * 插入数据
     *
     * @param artistPalett 请注意
     * @return {@link Long}
     */
    public Long insertData(ArtistPalett artistPalett) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("color", artistPalett.getColor());
        values.put("colorTag",artistPalett.getColorTag());
        values.put("create_time", artistPalett.getCreateTime().getTime());
        return db.insert(ARTIST_PALETT_TABLE_NAME, null, values);
    }

    public List<ArtistPalett> queryAllFromDB() {
        SQLiteDatabase db = getWritableDatabase();
        List<ArtistPalett> artistList = new ArrayList<>();
        Cursor cursor = db.query(ARTIST_PALETT_TABLE_NAME, null, null, null, null, null, "create_time DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String color = cursor.getString(cursor.getColumnIndex("color"));
                @SuppressLint("Range") String colorTag = cursor.getString(cursor.getColumnIndex("colorTag"));
                @SuppressLint("Range") Long createTimestamp = cursor.getLong(cursor.getColumnIndex("create_time"));
                ArtistPalett artistPalett = ArtistPalett.builder().id(id).color(color).colorTag(colorTag).createTime(new Date(createTimestamp)).build();
                artistList.add(artistPalett);
            }
        }
        cursor.close();
        db.close();
        return artistList;
    }

    public int updateDate(ArtistPalett artistPalett) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("color", artistPalett.getColor());
        values.put("colorTag",artistPalett.getColorTag());
        values.put("update_time", artistPalett.getCreateTime().getTime());
        return db.update(ARTIST_PALETT_TABLE_NAME, values, "id = ?", new String[]{String.valueOf(artistPalett.getId())});
    }

    public int deleteDataFromid(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ARTIST_PALETT_TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

}
