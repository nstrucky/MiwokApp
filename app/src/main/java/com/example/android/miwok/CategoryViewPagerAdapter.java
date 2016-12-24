package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.miwok.fragments.ColorsFragment;
import com.example.android.miwok.fragments.FamilyFragment;
import com.example.android.miwok.fragments.NumbersFragment;
import com.example.android.miwok.fragments.PhrasesFragment;

/**
 * Created by root on 12/24/16.
 */

public class CategoryViewPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 4;
    private String[] pageTitles = {"Numbers", "Family Members", "Colors", "Phrases"};

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }

    public CategoryViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new NumbersFragment();

            case 1:
                return new FamilyFragment();

            case 2:
                return new ColorsFragment();

            case 3:
                return new PhrasesFragment();

        }

        return null;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
