package com.example.esake;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.esake.databinding.ActivityStatsManagerPagesBinding;
import com.example.esake.ui.tabbedView_statsManager.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Stats_Manager_Pages extends AppCompatActivity {

    private ActivityStatsManagerPagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsManagerPagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }
}