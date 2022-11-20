package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.myapplication.R;
import com.example.myapplication.bean.Note;
import com.example.myapplication.databaseHelper.NotepadSqliteOpenHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

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
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.note_swipe_layout;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.note_list_layout, null);
        SwipeLayout swipeLayout = (SwipeLayout) v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
            }
        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
            }
        });
//        v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "click delete", Toast.LENGTH_SHORT).show();
//            }
//        });
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
        return noteList.get(position).getId();
    }
}
