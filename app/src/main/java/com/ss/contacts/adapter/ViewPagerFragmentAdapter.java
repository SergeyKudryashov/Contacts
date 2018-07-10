package com.ss.contacts.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ss.contacts.fragment.ContactsFragment;
import com.ss.contacts.fragment.FavoritesFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    private void init() {
        mFragments = new ArrayList<>();
        mFragments.add(new ContactsFragment());
        mFragments.add(new FavoritesFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ContactsFragment.TITLE;
            case 1:
                return FavoritesFragment.TITLE;
            default:
                return null;
        }
    }
}
