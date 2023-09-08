package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_PIC;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class ArtistPalettPicture_Fragment extends Fragment {
        private View view;

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        }

    public static ArtistPalettPicture_Fragment newInstance (String sectionNumber) {
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
            return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

        private void initView() {

        }
}