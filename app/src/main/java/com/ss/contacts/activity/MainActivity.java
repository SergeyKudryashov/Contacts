package com.ss.contacts.activity;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ss.contacts.manager.Contacts;
import com.ss.contacts.R;
import com.ss.contacts.adapter.ViewPagerFragmentAdapter;
import com.ss.contacts.model.Contact;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mTabLayout = findViewById(R.id.tab_layout_main_activity);
        mViewPager = findViewById(R.id.view_pager_main_activity);

        mViewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
