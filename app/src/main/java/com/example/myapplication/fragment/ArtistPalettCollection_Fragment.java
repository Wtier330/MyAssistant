package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_COLLECTION;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class ArtistPalettCollection_Fragment extends Fragment {

    public static ArtistPalettCollection_Fragment newInstance (String sectionNumber) {
        ArtistPalettCollection_Fragment fragment = new ArtistPalettCollection_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_COLLECTION, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
}
