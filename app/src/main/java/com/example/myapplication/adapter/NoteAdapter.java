package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.activity.Note_Edit;
import com.example.myapplication.activity.Notepad_Main;
import com.example.myapplication.bean.Note;
import com.example.myapplication.R;
import com.example.myapplication.databaseHelper.NotepadSqliteOpenHelper;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import lombok.Setter;

import org.apache.commons.lang3.time.DateUtils;

public class NoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Note> mynotelist;
    private LayoutInflater layoutInflater;
    private Context context;
    NotepadSqliteOpenHelper notepadSqliteOpenHelper;
    @Setter
    private int viewType;
    public static int TYPE_LINEAR_LAYOUT = 0;
    public static int TYPE_GRID_LAYOUT = 1;

    @SuppressLint("ResourceType")
    public NoteAdapter(Context context, List<Note> mBeanList) {
        this.mynotelist = mBeanList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        notepadSqliteOpenHelper = new NotepadSqliteOpenHelper(context);
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LINEAR_LAYOUT) {
            View v = layoutInflater.inflate(R.layout.note_list_layout, parent, false);
            MyNoteViewHolder myNoteViewHolder = new MyNoteViewHolder(v);
            return myNoteViewHolder;
        } else if (viewType == TYPE_GRID_LAYOUT) {
            View v = layoutInflater.inflate(R.layout.note_list_grid_layout, parent, false);
            MyNoteGridViewHolder myNoteGridViewHolder = new MyNoteGridViewHolder(v);
            return myNoteGridViewHolder;
        }
        return null;
    }

    public void refreshData(List<Note> notes) {
        this.mynotelist = notes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = mynotelist.get(position);
        /*
         * 判断holder属于哪一个实体
         * tips: 两个方法不能写一起，类型不同，需要单独写出来进行绑定
         * */
        if (holder == null) {
            return;
        }
        if (holder instanceof MyNoteViewHolder) {
            onBindLLViewHolder((MyNoteViewHolder) holder, position);
        } else if (holder instanceof MyNoteGridViewHolder) {
            onBindGridViewHolder((MyNoteGridViewHolder) holder, position);
        }

    }

    private void onBindLLViewHolder(@NonNull MyNoteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = mynotelist.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvcontent.setText(note.getContent());
        holder.tvtime.setText(note.getCreateTimeAsString());
        holder.tvdate.setText(note.getCreateDateAsString());

        if (position == 0) {
            holder.tvdate.setVisibility(View.VISIBLE);
        } else {
            Note prevNote = mynotelist.get(position - 1);
            if (!DateUtils.isSameDay(note.getCreateTime(), prevNote.getCreateTime())) {
                holder.tvdate.setVisibility(View.VISIBLE);
            } else {
                LinearLayout linearLayout = ((AppCompatActivity) context).findViewById(R.id.rl_note_item_wrapper);
                ViewGroup.LayoutParams p = linearLayout.getLayoutParams();
                p.height = ViewGroup.LayoutParams.MATCH_PARENT;
                linearLayout.setLayoutParams(p);
            }
        }

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
            private View dialogView;
            private View itemView;

            @Override
            public boolean onLongClick(View v) {
                itemView = holder.itemView;
                Dialog dialog = new Dialog(context, android.R.style.ThemeOverlay_Material_Dialog_Alert);
                dialogView = layoutInflater.inflate(R.layout.note_list_item_dialog, null);

                TextView tvdelete = dialogView.findViewById(R.id.note_item_delete);
                TextView tvedit = dialogView.findViewById(R.id.note_item_edit);

                /*
                 * 长按删除数据库中的数据
                 * */
                tvdelete.setOnClickListener(v1 -> {
                    dialog.dismiss();

                    Snackbar
                            .make(itemView, "是否删除?", Snackbar.LENGTH_LONG)
                            .setAnchorView(((AppCompatActivity) context).findViewById(R.id.fbt_note_add))
                            .setAction("确认", view -> {
                                int row = notepadSqliteOpenHelper.deleteDataFromid(note.getId());
                                if (row > 0) {
                                    deleteItem(position);
                                    Snackbar.make(itemView, "删除成功", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    Snackbar.make(itemView, "删除失败", Snackbar.LENGTH_SHORT).show();
                                }
                            }).show();
                });
                /*
                 * 长按编辑
                 * */
                tvedit.setOnClickListener(v12 -> {
                    Intent intent = new Intent(context, Note_Edit.class);
                    intent.putExtra("note", note);
                    context.startActivity(intent);
                    dialog.dismiss();
                });
                dialog.setContentView(dialogView);
                dialog.show();
                return false;
            }
        });
    }

    private void onBindGridViewHolder(@NonNull MyNoteGridViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = mynotelist.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvcontent.setText(note.getContent());
        holder.tvtime.setText(note.getCreateTimeAsString());
        holder.tvdate.setText(note.getCreateDateAsString());

        if (position == 0) {
            holder.tvdate.setVisibility(View.VISIBLE);
        } else {
            Note prevNote = mynotelist.get(position - 1);
            if (!DateUtils.isSameDay(note.getCreateTime(), prevNote.getCreateTime())) {
                holder.tvdate.setVisibility(View.VISIBLE);
            } else {
                LinearLayout linearLayout = ((AppCompatActivity) context).findViewById(R.id.rl_note_item_wrapper);
                ViewGroup.LayoutParams p = linearLayout.getLayoutParams();
                p.height = ViewGroup.LayoutParams.MATCH_PARENT;
                linearLayout.setLayoutParams(p);
            }
        }

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
            private View dialogView;
            private View itemView;

            @Override
            public boolean onLongClick(View v) {
                itemView = holder.itemView;
                Dialog dialog = new Dialog(context, android.R.style.ThemeOverlay_Material_Dialog_Alert);
                dialogView = layoutInflater.inflate(R.layout.note_list_item_dialog, null);

                TextView tvdelete = dialogView.findViewById(R.id.note_item_delete);
                TextView tvedit = dialogView.findViewById(R.id.note_item_edit);

                /*
                 * 长按删除数据库中的数据
                 * */
                tvdelete.setOnClickListener(v1 -> {
                    dialog.dismiss();

                    Snackbar
                            .make(itemView, "是否删除?", Snackbar.LENGTH_LONG)
                            .setAnchorView(((AppCompatActivity) context).findViewById(R.id.fbt_note_add))
                            .setAction("确认", view -> {
                                int row = notepadSqliteOpenHelper.deleteDataFromid(note.getId());
                                if (row > 0) {
                                    deleteItem(position);
                                    Snackbar.make(itemView, "删除成功", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    Snackbar.make(itemView, "删除失败", Snackbar.LENGTH_SHORT).show();
                                }
                            }).show();
                });
                /*
                 * 长按编辑
                 * */
                tvedit.setOnClickListener(v12 -> {
                    Intent intent = new Intent(context, Note_Edit.class);
                    intent.putExtra("note", note);
                    context.startActivity(intent);
                    dialog.dismiss();
                });
                dialog.setContentView(dialogView);
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

    /*
     * 列表布局适配器
     * */
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

    /*
     * 网格布局适配器
     * */
    class MyNoteGridViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvcontent;
        TextView tvtime;
        TextView tvdate;
        ViewGroup rlcontainer;

        public MyNoteGridViewHolder(@NonNull View itemView) {
            super(itemView);
            this.rlcontainer = itemView.findViewById(R.id.rl_note_item_container);
            this.tvTitle = itemView.findViewById(R.id.tv_note_title);
            this.tvcontent = itemView.findViewById(R.id.tv_note_content);
            this.tvtime = itemView.findViewById(R.id.tv_note_time);
            this.tvdate = itemView.findViewById(R.id.tv_note_date);
        }
    }

}