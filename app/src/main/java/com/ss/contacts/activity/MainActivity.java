package com.ss.contacts.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ss.contacts.R;
import com.ss.contacts.adapter.HasContactAdapter;
import com.ss.contacts.adapter.ViewPagerFragmentAdapter;
import com.ss.contacts.fragment.ContactsFragment;
import com.ss.contacts.fragment.FavoritesFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_LIST_KEY = "fragment.list";
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mFragmentList = (List<Fragment>) savedInstanceState.getSerializable(FRAGMENT_LIST_KEY);
        } else {
            mFragmentList = new ArrayList<>();
            mFragmentList.add(new ContactsFragment());
            mFragmentList.add(new FavoritesFragment());
        }
        init();
    }

    private void init() {
        TabLayout tabLayout = findViewById(R.id.tab_layout_main_activity);
        ViewPager viewPager = findViewById(R.id.view_pager_main_activity);

        final ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((HasContactAdapter) adapter.getItem(position)).notifyAdapter();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(FRAGMENT_LIST_KEY, (Serializable) mFragmentList);
    }
}