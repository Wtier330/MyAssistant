package com.example.myapplication.activity;

import static com.example.myapplication.constants.Otherconstants.KEY_NOTE_LAYOUT_MODE;
import static com.example.myapplication.constants.Otherconstants.TYPE_GRID_LAYOUT;
import static com.example.myapplication.constants.Otherconstants.TYPE_LINEAR_LAYOUT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.bean.Note;
import com.example.myapplication.database.NotepadSqliteOpenHelper;
import com.example.myapplication.utils.NoteSpfUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Notepad_Main extends AppCompatActivity implements NoteAdapter.IonSlidingViewClickListener {

    RecyclerView rlv_note;
    FloatingActionButton fbt_note_add;
    private List<Note> mNotes;
    private NotepadSqliteOpenHelper notepadSqliteOpenHelper;
    private NoteAdapter noteAdapter;
    private int currentListLayoutMode = TYPE_LINEAR_LAYOUT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_main);
        viewinit();
        DBinit();
        Eventinit();
    }

    /*
     *保存当前的布局选项
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;
        if (currentListLayoutMode == TYPE_LINEAR_LAYOUT) {
            item = menu.findItem(R.id.menu_note_linear).setChecked(true);
        } else if (currentListLayoutMode == TYPE_GRID_LAYOUT) {
            item = menu.findItem(R.id.menu_note_grid).setChecked(true);

        }
        return super.onPrepareOptionsMenu(menu);
    }

    @SuppressLint("ResourceType")
    private void Eventinit() {
        noteAdapter = new NoteAdapter(this, mNotes);
        rlv_note.setAdapter(noteAdapter);
        rlv_note.setItemAnimator(new DefaultItemAnimator());
        setToLL();
    }

    private void DBinit() {
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(this);
        SQLiteDatabase db = notepadSqliteOpenHelper.getWritableDatabase();
        if (db == null) {
            db.execSQL(NotepadSqliteOpenHelper.createtablesql);
        }
        mNotes = new ArrayList<>();
    }

    /*
     *    从数据库中得到数据
     */
    private List<Note> getDataFromDB() {
        return notepadSqliteOpenHelper.queryAllFromDB();
    }

    private void viewinit() {
        rlv_note = findViewById(R.id.rlv_note);
        fbt_note_add = findViewById(R.id.fbt_note_add);
    }

    public void addNote(View view) {
        startActivity(new Intent(this, Note_Add.class));
    }

    /*
     * 回到界面刷新
     * */
    @Override
    protected void onResume() {
        super.onResume();
        DBinit();//没有表的时候，打开会崩，因此需要执行一个数据库初始化
        refreshDataFromDB();
        setLayout();

    }

    private void setToGL() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rlv_note.setLayoutManager(gridLayoutManager);
        noteAdapter.setViewType(TYPE_GRID_LAYOUT);
        noteAdapter.notifyDataSetChanged();
    }

    private void setToLL() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlv_note.setLayoutManager(linearLayoutManager);
        noteAdapter.setViewType(TYPE_LINEAR_LAYOUT);
        noteAdapter.notifyDataSetChanged();
    }

    private void setLayout() {
        currentListLayoutMode = NoteSpfUtil.getIntWithDefault(this, KEY_NOTE_LAYOUT_MODE, TYPE_LINEAR_LAYOUT);
        if (currentListLayoutMode == TYPE_LINEAR_LAYOUT) {
            setToLL();
        } else if (currentListLayoutMode == TYPE_GRID_LAYOUT) {
            setToGL();
        }
    }

    private void refreshDataFromDB() {
        mNotes = getDataFromDB();
        noteAdapter.refreshData(mNotes);
    }

    /*
     * 创建菜单
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_note_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mNotes = notepadSqliteOpenHelper.quearyFromDbByTitle(newText);
                noteAdapter.refreshData(mNotes);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * 选中菜单
     * */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.menu_note_linear:
                setToLL();
                currentListLayoutMode = TYPE_LINEAR_LAYOUT;
                NoteSpfUtil.saveInt(this, KEY_NOTE_LAYOUT_MODE, currentListLayoutMode);
                return true;
            case R.id.menu_note_grid:
                setToGL();
                currentListLayoutMode = TYPE_GRID_LAYOUT;
                NoteSpfUtil.saveInt(this, KEY_NOTE_LAYOUT_MODE, currentListLayoutMode);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    /**
     * item正文的点击事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        //点击item正文的代码逻辑
        Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
    }


    /**
     * item的左滑编辑
     *
     * @param view
     * @param position
     */
    @Override
    public void onSetBtnCilck(View view, int position) {
        Note note = mNotes.get(position);
        Intent intent = new Intent(getApplicationContext(), Note_Edit.class);
        intent.putExtra("note", note);
        startActivity(intent);
        //“设置”点击事件的代码逻辑
        Toast.makeText(Notepad_Main.this, "请设置", Toast.LENGTH_LONG).show();
    }


    /**
     * item的左滑删除
     * 此处该方法已经进行item更新，不需要重复取值导致越界
     *
     * @param view
     * @param position
     */
    @Override
    public void onDeleteBtnCilck(View view, int position) {
        Note note = mNotes.get(position);
        noteAdapter.removeData(position);
        int row = notepadSqliteOpenHelper.deleteDataFromid(note.getId());
        if (row > 0) {
            Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
        }
    }


}