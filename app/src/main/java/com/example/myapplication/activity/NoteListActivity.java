package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.adapter.NoteListAdapter;
import com.example.myapplication.bean.Note;
import com.example.myapplication.databaseHelper.NotepadSqliteOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
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
        DBinit();
        Eventinit();
    }

    @SuppressLint("ResourceType")
    private void Eventinit() {
        noteAdapter = new NoteListAdapter(this);
        rlv_note.setAdapter(noteAdapter);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rlv_note.setLayoutManager(linearLayoutManager);

    }

    private void DBinit() {
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(this);
        mNotes = new ArrayList<>();
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
        refreshDataFromDB();
    }

    private void refreshDataFromDB() {
        mNotes = notepadSqliteOpenHelper.queryAllFromDB();
//        noteAdapter.refreshData(mNotes);
    }

}
