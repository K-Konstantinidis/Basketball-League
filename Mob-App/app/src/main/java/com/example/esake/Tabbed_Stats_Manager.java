package com.example.esake;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.esake.databinding.ActivityTabbedStatsManagerBinding;
import com.example.esake.ui.tabbedView_statsManager.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Tabbed_Stats_Manager extends AppCompatActivity {

    private ActivityTabbedStatsManagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabbedStatsManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPagerSm;

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabsSm;
        tabs.setupWithViewPager(viewPager);
    }
}