package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.myapplication.R;
import com.example.myapplication.activity.Note_Edit;
import com.example.myapplication.bean.Note;
import com.example.myapplication.databaseHelper.NotepadSqliteOpenHelper;
import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private List<Note> noteList = new ArrayList<>();

    NotepadSqliteOpenHelper notepadSqliteOpenHelper;

    public NoteListAdapter(Context mContext) {
        this.mContext = mContext;
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(mContext);
        fetchNoteList();
    }

    public void fetchNoteList() {
        noteList = notepadSqliteOpenHelper.queryAllFromDB();
        this.notifyDataSetChanged();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.note_swipe_layout;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.note_list_layout, null);
        SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(getSwipeLayoutResourceId(position));

        v.findViewById(R.id.btn_note_item_edit).setOnClickListener(view -> {
            Note currentNote = (Note) getItem(position);

            Intent intent = new Intent(mContext, Note_Edit.class);
            intent.putExtra("note", currentNote);
            mContext.startActivity(intent);
        });
        v.findViewById(R.id.btn_note_item_del).setOnClickListener(view -> {
            Note currentNote = (Note) getItem(position);

            Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_SHORT).setAnchorView(((AppCompatActivity) mContext).findViewById(R.id.fbt_note_add));
            int row = notepadSqliteOpenHelper.deleteDataFromid(currentNote.getId());
            if (row > 0) {
                snackbar.setText("删除成功").show();
                fetchNoteList();
            } else {
                snackbar.setText("删除失败").show();
            }
            swipeLayout.close();
        });
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        Note currentNote = (Note) getItem(position);

        TextView tv_note_title = (TextView) convertView.findViewById(R.id.tv_note_title);
        TextView tv_note_content = (TextView) convertView.findViewById(R.id.tv_note_content);
        TextView tv_note_time = (TextView) convertView.findViewById(R.id.tv_note_time);
        TextView tv_note_date = (TextView) convertView.findViewById(R.id.tv_note_date);
        tv_note_title.setText(currentNote.getTitle());
        tv_note_content.setText(StringUtils.abbreviate(currentNote.getContent(), "...", 30));
        tv_note_time.setText(currentNote.getCreateTimeAsString());
        tv_note_date.setText(currentNote.getCreateDateAsString());

        if (position == 0) {
            tv_note_date.setVisibility(View.VISIBLE);
        } else {
            Note prevNote = (Note) getItem(position - 1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            System.out.println(position + ".currentNote -> " + sdf.format(currentNote.getCreateTime()));
            System.out.println(position + ".prevNote -> " + sdf.format(prevNote.getCreateTime()));


            if (!DateUtils.isSameDay(currentNote.getCreateTime(), prevNote.getCreateTime())) {
                tv_note_date.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Note) getItem(position)).getId();
    }
}
