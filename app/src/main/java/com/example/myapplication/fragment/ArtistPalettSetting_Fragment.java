package com.example.myapplication.fragment;


import static com.example.myapplication.constants.Fragmentconstants.SECTION_SET;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class ArtistPalettSetting_Fragment extends Fragment {
    private View view;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
    public static ArtistPalettSetting_Fragment newInstance (String sectionNumber) {
        ArtistPalettSetting_Fragment fragment = new ArtistPalettSetting_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_SET, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artistpalett_setting_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {

    }
}
