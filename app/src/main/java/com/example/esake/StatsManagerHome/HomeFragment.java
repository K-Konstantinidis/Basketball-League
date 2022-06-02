package com.example.esake.StatsManagerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.esake.R;
import com.example.esake.Stats_Manager_Pages;
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

        Button b = (Button)v.findViewById(R.id.watchGame);
        b.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Stats_Manager_Pages.class);
                startActivity(intent);

            }

        });

        return v;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}