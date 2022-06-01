package com.example.esake.StatsManagerHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.esake.R;
import com.example.esake.databinding.FragmentHomeStatsManagerBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeStatsManagerBinding binding;

    public HomeFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeStatsManagerBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textView;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;

        View v = inflater.inflate(R.layout.fragment_home_stats_manager,null);
        return v;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}