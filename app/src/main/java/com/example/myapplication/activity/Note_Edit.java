package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.bean.Note;
import com.example.myapplication.database.NotepadSqliteOpenHelper;
import com.example.myapplication.utils.ToastUtil;

import java.util.Date;

public class Note_Edit extends AppCompatActivity {
    private Note note;
    private EditText et_note_edittitle, et_note_editinfo;
    NotepadSqliteOpenHelper notepadSqliteOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        viewinit();
        Datainit();
    }

    private void Datainit() {
        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if (note != null) {
            et_note_edittitle.setText(note.getTitle());
            et_note_editinfo.setText(note.getContent());
        }
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(this);
    }

    private void viewinit() {
        et_note_edittitle = findViewById(R.id.et_note_edittitle);
        et_note_editinfo = findViewById(R.id.et_note_editinfo);

    }

    public void noteEditsave(View view) {
        String title = et_note_edittitle.getText().toString().trim();
        String content = et_note_editinfo.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            ToastUtil.toastShort(this, "标题不能为空!");
            return;
        }
//        Note note = Note.builder().title(title).content(content).createTime(new Date().toString()).build();
        note.setTitle(title);
        note.setContent(content);
        note.setUpdateTime(new Date());
        long row = notepadSqliteOpenHelper.updateDate(note);
        if (row != -1){
            ToastUtil.toastShort(this,"修改成功!");
            this.finish();
        }else {
            ToastUtil.toastShort(this,"修改失败!");
        }
    }
}