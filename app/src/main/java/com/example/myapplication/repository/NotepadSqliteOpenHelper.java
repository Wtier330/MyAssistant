package com.example.myapplication.repository;



import static com.example.myapplication.constants.DBconstants.DB_NAME;
import static com.example.myapplication.constants.DBconstants.NOTE_TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.example.myapplication.model.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

public class NotepadSqliteOpenHelper extends SQLiteOpenHelper {


    /**
     * id: 表主键
     * title: 笔记标题
     * content: 笔记内容
     * create_time: 创建时间 (时间戳)
     * update_time: 更新时间 (时间戳)
     */
    public static final String createtablesql = "CREATE TABLE IF NOT EXISTS "
            + NOTE_TABLE_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " title TEXT NOT NULL, content TEXT NOT NULL," +
            " create_time INTEGER, update_time INTEGER)";

    public NotepadSqliteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
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
        values.put("create_time", note.getCreateTime().getTime());
        return db.insert(NOTE_TABLE_NAME, null, values);
    }

    public List<Note> queryAllFromDB() {
        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = db.query(NOTE_TABLE_NAME, null, null, null, null, null, "create_time DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") Long createTimestamp = cursor.getLong(cursor.getColumnIndex("create_time"));
                @SuppressLint("Range") Long updateTimestamp = cursor.getLong(cursor.getColumnIndex("update_time"));
                Note note = Note.builder().id(id).title(title).content(content).createTime(new Date(createTimestamp)).updateTime(new Date(updateTimestamp)).build();
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
        values.put("update_time", note.getCreateTime().getTime());
        return db.update(NOTE_TABLE_NAME, values, "id = ?", new String[]{String.valueOf(note.getId())});
    }

    public int deleteDataFromid(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(NOTE_TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Note> quearyFromDbByTitle(String title) {
        if (TextUtils.isEmpty(title)) {        //判空，不输入则查询所有
            return queryAllFromDB();
        }
        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = db.query(NOTE_TABLE_NAME, null, "title like ?", new String[]{"%" + title + "%"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title2 = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") Long createTimestamp = cursor.getLong(cursor.getColumnIndex("create_time"));
                @SuppressLint("Range") Long updateTimestamp = cursor.getLong(cursor.getColumnIndex("update_time"));
                Note note = Note.builder().id(id).title(title2).content(content).createTime(new Date(createTimestamp)).updateTime(new Date(updateTimestamp)).build();
                noteList.add(note);
            }
        }
        cursor.close();
        db.close();
        return noteList;
    }
}

