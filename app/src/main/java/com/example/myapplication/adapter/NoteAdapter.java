package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.activity.Note_Edit;
import com.example.myapplication.bean.Note;
import com.example.myapplication.R;
import com.example.myapplication.databaseHelper.NotepadSqliteOpenHelper;
import com.example.myapplication.utils.ToastUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyNoteViewHolder> {
    private List<Note> mynotelist;
    private LayoutInflater layoutInflater;
    private Context context;
    NotepadSqliteOpenHelper notepadSqliteOpenHelper;

    public NoteAdapter(Context context, List<Note> mBeanList) {
        this.mynotelist = mBeanList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(context);

    }

    @NonNull
    @Override
    public MyNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.note_list_layout, parent, false);
        MyNoteViewHolder myNoteViewHolder = new MyNoteViewHolder(v);
        return myNoteViewHolder;
    }

    public void refreshData(List<Note> notes) {
        this.mynotelist = notes;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull MyNoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = mynotelist.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvcontent.setText(note.getContent());
        holder.tvtime.setText(note.getCreateTimeAsString());
        holder.tvdate.setText(note.getCreateDateAsString());
        /*
         * 在点击列表的item条目的时候，能够进行跳转
         * 将note参数进行一个传递
         * */
        holder.rlcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Note_Edit.class);
                intent.putExtra("note", note);
                context.startActivity(intent);
            }
        });
        /*
         * 长按弹出弹窗
         * 删除或者编辑
         * */
        holder.rlcontainer.setOnLongClickListener(new View.OnLongClickListener() {

            private View view;

            @Override
            public boolean onLongClick(View v) {
                //TODO dialog样式未设计
                Dialog dialog = new Dialog(context);
                view = layoutInflater.inflate(R.layout.note_list_item_dialog, null);
                TextView tvdelete = view.findViewById(R.id.note_item_delete);
                TextView tvedit = view.findViewById(R.id.note_item_edit);

                /*
                 * 长按删除数据库中的数据
                 * */
                tvdelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int row = notepadSqliteOpenHelper.deleteDataFromid(note.getId());
                        if (row > 0) {
                            deleteItem(position);
                            ToastUtil.toastShort(context, "删除成功");
                        } else {
                            ToastUtil.toastShort(context, "删除失败");
                        }

                        dialog.dismiss();

                    }
                });
                /*
                * 长按编辑
                * */
                tvedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Note_Edit.class);
                        intent.putExtra("note", note);
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mynotelist.size();
    }

    public void deleteItem(int pos) {
        mynotelist.remove(pos);
        notifyItemRemoved(pos);
    }

    class MyNoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvcontent;
        TextView tvtime;
        TextView tvdate;
        ViewGroup rlcontainer;

        public MyNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rlcontainer = itemView.findViewById(R.id.rl_note_item_container);
            this.tvTitle = itemView.findViewById(R.id.tv_note_title);
            this.tvcontent = itemView.findViewById(R.id.tv_note_content);
            this.tvtime = itemView.findViewById(R.id.tv_note_time);
            this.tvdate = itemView.findViewById(R.id.tv_note_date);
        }
    }

}