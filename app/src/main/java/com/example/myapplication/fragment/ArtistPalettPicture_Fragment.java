package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_PIC;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.bean.ArtistPalett;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author WTL
 * @date 2023/09/13
 * 随机生成颜色
 */
public class ArtistPalettPicture_Fragment extends Fragment {
    private View view;
    private ListView rlv_artist;
    private List<ArtistPalett> artistPalett = new ArrayList<>();
    private Button bt_artist_reggenerate;
    private EditText etArtistpalettPicInputcount;
    private ArtistPalett artist;
    private List<String> colors = new ArrayList<>();
    private List<Integer> generatedColors = new ArrayList<>();
    private LayoutInflater inflater;
    private ColorAdapter colorAdapter = new ColorAdapter();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static ArtistPalettPicture_Fragment newInstance(String sectionNumber) {
        ArtistPalettPicture_Fragment fragment = new ArtistPalettPicture_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_PIC, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artistpalett_picture_fragment, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initEvent() {
        bt_artist_reggenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;
                /**
                 * 生成随机颜色值
                 * */
                if (!TextUtils.isEmpty(etArtistpalettPicInputcount.getText().toString())) {
                    count = Integer.parseInt(etArtistpalettPicInputcount.getText().toString());
                } else {
                    count = 10;
                }
                randomColor(count);
                colorAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        rlv_artist = view.findViewById(R.id.rlv_artist);
//        artistAdapter = new ArtistAdapter(getActivity(), artistPalett);
        rlv_artist.setAdapter(colorAdapter);

        bt_artist_reggenerate = view.findViewById(R.id.bt_artist_reggenerate);
        etArtistpalettPicInputcount = view.findViewById(R.id.et_artistpalett_pic_Inputcount);

    }

    /*
     * 该方法用随机数进行生成不重复的颜色并存入数组
     * */
    private void randomColor(int numberOfColorsToGenerate) {
        Random random = new Random();

        for (int i = 0; i < numberOfColorsToGenerate; i++) {
            int red, green, blue;
            int rgb;

            // 生成不重复的颜色值
            do {
                red = random.nextInt(256);
                green = random.nextInt(256);
                blue = random.nextInt(256);
                rgb = (red << 16) | (green << 8) | blue;
            } while (artistPalett.contains(rgb));

            generatedColors.add(rgb);

            // 转换为十六进制表示
            String hexColor = String.format("#%06X", rgb);
            colors.add(hexColor);
            System.out.println("随机生成的颜色值 " + (i + 1) + ": " + hexColor);
        }
    }

    //    class ColorHolder extends RecyclerView.ViewHolder {
//        public TextView tv_color;
//        public TextView tv_tag;
//        public View colorshow;
//
//        public ColorHolder(@NonNull View itemView) {
//            super(itemView);
//            colorshow = itemView.findViewById(R.id.color_view);
//            tv_color = itemView.findViewById(R.id.tv_color_colorhex);
//            tv_tag = itemView.findViewById(R.id.tv_color_colorTag);
//        }
//    }
//
//    class ColorAdapter extends RecyclerView.Adapter<ColorHolder> {
//        @NonNull
//        @Override
//        public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            inflater = LayoutInflater.from(getActivity());
//            View v = inflater.inflate(R.layout.color_list, parent, false);
//            ColorHolder colorHolder = new ColorHolder(v);
//            return colorHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ColorHolder holder, int position) {
//            if (holder == null) {
//                return;
//            }
//            randomColor(10);
//            holder.tv_color.setText(colors.get(position));
//            holder.colorshow.setBackgroundColor(generatedColors.get(position));
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public int getItemCount() {
//            return colors.size();
//        }
//    }
    class ColorAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return colors.size();
        }

        @Override
        public Object getItem(int position) {
            return colors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(getActivity(), R.layout.color_list, null);
            View colorshow = v.findViewById(R.id.color_view);
            TextView tv_hex = v.findViewById(R.id.tv_color_colorhex);
            TextView tv_tag = v.findViewById(R.id.tv_color_colorTag);

            tv_hex.setText(colors.get(position));
            colorshow.setBackgroundColor(Color.parseColor(colors.get(position)));
            return v;
        }
    }
}