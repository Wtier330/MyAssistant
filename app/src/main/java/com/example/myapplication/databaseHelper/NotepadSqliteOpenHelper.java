package com.example.myapplication.databaseHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.bean.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

public class NotepadSqliteOpenHelper extends SQLiteOpenHelper {
    public static final String NOTE_DB_NAME = "noteSQLite.db";
    public static final String NOTE_TABLE_NAME = "note";
    public static final String createtablesql = "CREATE TABLE IF NOT EXISTS "
            + NOTE_TABLE_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title text, content text, create_time text)";

    public NotepadSqliteOpenHelper(@Nullable Context context) {
        super(context, NOTE_DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtablesql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入数据
     *
     * @param note 请注意
     * @return {@link Long}
     */
    public Long insertData(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreateTimeAsString());
        return db.insert(NOTE_TABLE_NAME, null, values);
    }

    public List<Note> queryAllFromDB() {
        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = db.query(NOTE_TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String createtime = cursor.getString(cursor.getColumnIndex("create_time"));
                Note note = Note.builder().id(id).title(title).content(content).createTime(createtime).build();
                noteList.add(note);

            }
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public int updateDate(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("content", note.getContent());
        values.put("create_time", note.getCreateTime());
        return db.update(NOTE_TABLE_NAME, values, "id like ?", new String[]{note.getId()});
    }

    public int deleteDataFromid(String id) {
        SQLiteDatabase db = getWritableDatabase()   ;

        return db.delete(NOTE_TABLE_NAME,"id like ?",new String[]{id});
    }
}

