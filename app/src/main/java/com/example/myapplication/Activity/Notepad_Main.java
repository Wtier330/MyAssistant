package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Adapter.NoteAdapter;
import com.example.myapplication.Bean.Note;
import com.example.myapplication.DataBaseHelper.NotepadSqliteOpenHelper;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notepad_Main extends AppCompatActivity {
    RecyclerView rlv_note;
    FloatingActionButton fbt_note_add;
    private List<Note> mNotes;
    private NotepadSqliteOpenHelper notepadSqliteOpenHelper;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_main);
        viewinit();
        DBinit();
        Eventinit();
    }

    private void Eventinit() {
        noteAdapter = new NoteAdapter(this, mNotes);
        rlv_note.setAdapter(noteAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlv_note.setLayoutManager(linearLayoutManager);
    }

    private void DBinit() {
        mNotes = new ArrayList<>();

        Note note = new Note();
        note.setTitle("title test 1");
        note.setContent("content text 1");
        note.setCreatedTime(getCurrentTimeFormat());
        mNotes.add(note);
        /*
         * 假数据测试代码
         * */
        for (int i = 0; i < 29; i++) {
            Note note1 = new Note();
            note1.setTitle("title test 2" + i);
            note1.setContent("content text 2" + i);
            note1.setCreatedTime(getCurrentTimeFormat());
            mNotes.add(note1);
        }
    }

    private String getCurrentTimeFormat() {
        SimpleDateFormat s = new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");
        Date d = new Date();
        return s.format(d);
    }

    private void viewinit() {
        rlv_note = findViewById(R.id.rlv_note);
        fbt_note_add = findViewById(R.id.fbt_note_add);

    }

    public void add(View view) {

    }
}