package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_COLLECTION;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class ArtistPalettCollection_Fragment extends Fragment {

private TextView tv_artistpalett_collection_count;
private ImageView iv_artistpalett_collection_favourite,iv_artistpalett_collection_share,iv_artistpalett_collection_back;
private RecyclerView rl_artistpalett_collection_list;
    public static ArtistPalettCollection_Fragment newInstance(String sectionNumber) {
        ArtistPalettCollection_Fragment fragment = new ArtistPalettCollection_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_COLLECTION, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artistpalett_collection_fragment, container, false);
        initview(view);
        initEvent();
        return view;
    }

    private void initEvent() {
    }

    private void initview(View view) {


    }
}
