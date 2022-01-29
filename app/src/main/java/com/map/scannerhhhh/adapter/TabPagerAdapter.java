package com.map.scannerhhhh.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.map.scannerhhhh.ui.occupation.sallesParBloc.SallesParBlocFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    private static final int total_pages = 3;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        
        switch (position) {
            case 0:
                return new SallesParBlocFragment("61b8fddce9bf24288814d5e6");
            case 1:
                return new SallesParBlocFragment("61d1b5feddd61433f4149eec");
            case 2:
                return new SallesParBlocFragment("61d1b603ddd61433f4149eef");
        }
        return null;
    }

    @Override
    public int getCount() {
        return total_pages;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Bloc A";
            case 1:
                return "Bloc B";
            case 2:
                return "Bloc C";
        }
        return null;
    }

}
