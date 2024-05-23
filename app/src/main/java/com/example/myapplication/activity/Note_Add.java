package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.model.Note;
import com.example.myapplication.repository.NotepadSqliteOpenHelper;
import com.example.myapplication.utils.ToastUtil;

import java.util.Date;

public class Note_Add extends AppCompatActivity {
    EditText et_note_addtitle, et_note_addinfo;
    private NotepadSqliteOpenHelper notepadSqliteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        viewinit();
        DBinit();
    }

    private void DBinit() {
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(this);
    }

    private void viewinit() {
        et_note_addinfo = findViewById(R.id.et_note_addinfo);
        et_note_addtitle = findViewById(R.id.et_note_addtitle);
    }

    public void addNotetrue(View view) {
        String title = et_note_addtitle.getText().toString().trim();
        String content = et_note_addinfo.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.toastShort(this, "标题不能为空!");
            return;
        }
        Note note = Note.builder().title(title).content(content).createTime(new Date()).build();
        Long row = notepadSqliteOpenHelper.insertData(note);
        if (row != -1){
            ToastUtil.toastShort(this,"添加成功!");
            this.finish();
        }else {
            ToastUtil.toastShort(this,"添加失败!");
        }
    }
}