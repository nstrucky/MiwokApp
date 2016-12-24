package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by root on 12/24/16.
 */

public class CategoryViewPagerAdapter extends FragmentPagerAdapter {

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
        return 4;
    }
}
