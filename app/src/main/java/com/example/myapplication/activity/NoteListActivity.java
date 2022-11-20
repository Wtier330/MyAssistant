package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteListAdapter;
import com.example.myapplication.bean.Note;
import com.example.myapplication.databaseHelper.NotepadSqliteOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    GridView rlv_note;
    FloatingActionButton fbt_note_add;
    private List<Note> mNotes;
    private NotepadSqliteOpenHelper notepadSqliteOpenHelper;
    private NoteListAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_main);
        viewinit();
        Eventinit();
    }

    @SuppressLint("ResourceType")
    private void Eventinit() {
        noteAdapter = new NoteListAdapter(this);
        rlv_note.setAdapter(noteAdapter);
    }

    private void viewinit() {
        rlv_note = findViewById(R.id.rlv_note);
        fbt_note_add = findViewById(R.id.fbt_note_add);
    }

    public void addNote(View view) {
        startActivity(new Intent(this, Note_Add.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO 新增时可能会出现列表按日期分组错误的情况
        noteAdapter.fetchNoteList();
    }

}
