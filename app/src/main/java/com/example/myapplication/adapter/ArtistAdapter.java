package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.ArtistPalett;
import com.example.myapplication.database.ArtistPalettSqilteHelper;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ArtistPalett> myartistPaletts;

    private LayoutInflater layoutInflater;
    private ArtistPalettSqilteHelper artistPalettSqilteHelper;
    private Context context;

    public ArtistAdapter(Context context, List<ArtistPalett> myartistPaletts){
        this.myartistPaletts = myartistPaletts;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
        artistPalettSqilteHelper = new ArtistPalettSqilteHelper(context);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.color_list,parent,false);
        MycolorHoder mycolorHoder = new MycolorHoder(view);
        return mycolorHoder;
    }
    /*
    * 列表刷新
    * */
    public void refreshData(List<ArtistPalett> artistPaletts) {
        this.myartistPaletts = artistPaletts;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArtistPalett artistPalett = myartistPaletts.get(position);
        if (holder == null){
            return;
        }
        if (holder instanceof  MycolorHoder){
            bindview((MycolorHoder)holder,position);
        }
    }

    private void bindview(@NonNull MycolorHoder holder,@NonNull int position) {
        ArtistPalett artistPalett = myartistPaletts.get(position);
        holder.tv_colorHex.setText(artistPalett.getColor());
        holder.tv_colorTag.setText(artistPalett.getColorTag());

        /**
         * 点击列表item的对应控件能进行相应修改
         */
        holder.tv_colorTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 修改颜色的标识
                 */
            }
        });
        holder.tv_colorHex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击进行跳转到对应的颜色值
                 */
            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class MycolorHoder extends RecyclerView.ViewHolder{
        public View color_view;
        public TextView tv_colorHex,tv_colorTag;
        public MycolorHoder(@NonNull View itemView) {
            super(itemView);
            color_view = itemView.findViewById(R.id.color_view);
            tv_colorHex = itemView.findViewById(R.id.tv_color_colorhex);
            tv_colorTag = itemView.findViewById(R.id.tv_color_colorTag);

        }
    }

}
