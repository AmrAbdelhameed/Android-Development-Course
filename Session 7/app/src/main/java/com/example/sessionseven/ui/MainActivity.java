package com.example.sessionseven.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.sessionseven.R;
import com.example.sessionseven.ui.fragments.CameraFragment;
import com.example.sessionseven.ui.fragments.MusicFragment;
import com.example.sessionseven.ui.fragments.VoiceFragment;
import com.example.sessionseven.viewpager.ViewPagerAdapter;
import com.example.sessionseven.viewpager.ViewPagerItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getViewPagerItems());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private List<ViewPagerItem> getViewPagerItems() {
        List<ViewPagerItem> viewPagerItems = new ArrayList<>();
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_text_1), MusicFragment.newInstance()));
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_text_2), CameraFragment.newInstance()));
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_text_3), VoiceFragment.newInstance()));
        return viewPagerItems;
    }
}