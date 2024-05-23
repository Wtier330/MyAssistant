package com.example.myapplication.repository;

import static com.example.myapplication.constants.DBconstants.DATE_CALU_NAME;
import static com.example.myapplication.constants.DBconstants.DB_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.DateCalu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author witer330
 * @date 2024/04/22
 * id: 表主键
 * inputdate: 输入时间
 * dateTag: 备注
 * result: 运算结果
 */
public class DateCaluSqliteHelper extends SQLiteOpenHelper {
    public static final String createtablesql = "CREATE TABLE IF NOT EXISTS "
            + DATE_CALU_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " inputdate date NOT NULL,"
            + " inputvalue INTEGER NOT NULL,"
            + " name TEXT,"
            + " result TEXT,"
            + " create_time INTEGER NOT NULL,"
            + " update_time INTEGER)";

    public DateCaluSqliteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtablesql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Long insertData(DateCalu dateCalu) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("inputdate", dateCalu.getInputTime().getTime());
        values.put("inputvalue", dateCalu.getInputValue());
        values.put("name", dateCalu.getName());
        values.put("result", dateCalu.getResult());
        values.put("create_time", System.currentTimeMillis());
        return db.insert(DATE_CALU_NAME, null, values);
    }

    public List<DateCalu> queryAllFromDB() {
        SQLiteDatabase db = getWritableDatabase();
        List<DateCalu> dateCaluList = new ArrayList<>();
        Cursor cursor = db.query(DATE_CALU_NAME, null, null, null, null, null, "create_time DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") Long inputdate = cursor.getLong(cursor.getColumnIndex("inputdate"));
                @SuppressLint("Range") Long create_time = cursor.getLong(cursor.getColumnIndex("create_time"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String result = cursor.getString(cursor.getColumnIndex("result"));
                @SuppressLint("Range") int inputvalue = cursor.getInt(cursor.getColumnIndex("inputvalue"));
                DateCalu dateCalu = DateCalu.builder()
                        .id(id)
                        .createTime(new Date(create_time))
                        .inputTime(new Date(inputdate))
                        .inputValue(inputvalue)
                        .result(result).name(name)
                        .build();
                dateCaluList.add(dateCalu);
            }
        }
        cursor.close();
        db.close();
        return dateCaluList;
    }

    public int updateName(DateCalu dateCalu, String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("update_time",System.currentTimeMillis());
        values.put("name", name);
        return db.update(DATE_CALU_NAME, values, "id = ?", new String[]{String.valueOf(dateCalu.getId())});
    }

    public int deleteFromId(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(DATE_CALU_NAME, "id = ?", new String[]{String.valueOf(id)});
    }
}
