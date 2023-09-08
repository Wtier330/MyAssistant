package com.example.myapplication.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class FragmentIndexAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public FragmentIndexAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int fragment) {
        return fragments.get(fragment);
    }

    public int getCount() {
        return fragments.size();
    }

}

