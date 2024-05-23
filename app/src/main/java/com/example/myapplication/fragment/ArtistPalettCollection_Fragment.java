package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_COLLECTION;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ArtistAdapter;
import com.example.myapplication.model.ArtistPalett;
import com.example.myapplication.repository.ArtistPalettSqilteHelper;

import java.util.ArrayList;
import java.util.List;

public class ArtistPalettCollection_Fragment extends Fragment {

    private TextView tv_artistpalett_collection_count;
    private ImageView iv_artistpalett_collection_favourite, iv_artistpalett_collection_share, iv_artistpalett_collection_back;
    private RecyclerView rl_artistpalett_collection_list;
    private String[] colorHex, colorTag;
    private List<ArtistPalett> artistPaletts;
    private ArtistAdapter artistAdapter;
    private ArtistPalettSqilteHelper palettSqilteHelper;

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
        initDB();
        initEvent();

        return view;
    }

    private void initDB() {
        palettSqilteHelper = new ArtistPalettSqilteHelper(getActivity());
        SQLiteDatabase db = palettSqilteHelper.getWritableDatabase();
            db.execSQL(ArtistPalettSqilteHelper.createtablesql);
            artistPaletts = new ArrayList<>();

    }

    private void initEvent() {
        artistAdapter = new ArtistAdapter(getActivity(), artistPaletts);
        rl_artistpalett_collection_list.setAdapter(artistAdapter);
        rl_artistpalett_collection_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        artistAdapter.notifyDataSetChanged();
    }

    private void refreshDataFromDB() {
        artistPaletts = palettSqilteHelper.queryAllFromDB();
        artistAdapter.refreshData(artistPaletts);
        tv_artistpalett_collection_count.setText(artistPaletts.size() + " 条收藏");
    }

    @Override
    public void onResume() {
        super.onResume();
        initDB();
        refreshDataFromDB();

    }

    private void initview(View view) {
        tv_artistpalett_collection_count = view.findViewById(R.id.tv_artistpalett_collection_count);
        iv_artistpalett_collection_favourite = view.findViewById(R.id.iv_artistpalett_collection_favourite);
        rl_artistpalett_collection_list = view.findViewById(R.id.rl_artistpalett_collection_list);
        iv_artistpalett_collection_share = view.findViewById(R.id.iv_artistpalett_collection_share);

    }
}
