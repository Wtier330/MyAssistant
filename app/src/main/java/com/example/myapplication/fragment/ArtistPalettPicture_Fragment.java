package com.example.myapplication.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArtistPalettPicture_Fragment extends Fragment{
        private View view;

        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
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