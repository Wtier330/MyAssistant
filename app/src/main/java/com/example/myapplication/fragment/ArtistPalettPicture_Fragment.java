package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_PIC;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ArtistAdapter;
import com.example.myapplication.bean.ArtistPalett;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author WTL
 * @date 2023/09/13
 * 随机生成颜色
 */
public class ArtistPalettPicture_Fragment extends Fragment {
    private View view;
    private RecyclerView rlv_artist;
    private List<ArtistPalett> artistPalett;
    private ArtistAdapter artistAdapter;
    private Button bt_artist_reggenerate;
    private EditText etArtistpalettPicInputcount;

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
                /**
                * 生成随机颜色值
                * */
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        rlv_artist = view.findViewById(R.id.rlv_artist);
        artistAdapter = new ArtistAdapter(getActivity(), artistPalett);
        rlv_artist.setAdapter(artistAdapter);

        bt_artist_reggenerate = view.findViewById(R.id.bt_artist_reggenerate);
        etArtistpalettPicInputcount = view.findViewById(R.id.et_artistpalett_pic_Inputcount);

    }

    /*
     * 该方法用随机数进行生成不重复的颜色
     * */
    private void randomColor(int numberOfColorsToGenerate) {
        Random random = new Random();
        Set<Integer> generatedColors = new HashSet<>();

        for (int i = 0; i < numberOfColorsToGenerate; i++) {
            int red, green, blue;
            int rgb;

            // 生成不重复的颜色值
            do {
                red = random.nextInt(256);
                green = random.nextInt(256);
                blue = random.nextInt(256);
                rgb = (red << 16) | (green << 8) | blue;
            } while (generatedColors.contains(rgb));

            generatedColors.add(rgb);

            // 转换为十六进制表示
            String hexColor = String.format("#%06X", rgb);
            System.out.println("随机生成的颜色值 " + (i + 1) + ": " + hexColor);
        }
    }
}