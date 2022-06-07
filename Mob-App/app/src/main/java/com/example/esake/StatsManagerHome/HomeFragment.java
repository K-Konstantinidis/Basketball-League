package com.example.esake.StatsManagerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.esake.Championship;
import com.example.esake.GameWeek;
import com.example.esake.R;
import com.example.esake.Tabbed_Stats_Manager;
import com.example.esake.databinding.FragmentHomeStatsManagerBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

	private FragmentHomeStatsManagerBinding binding;

	private ArrayList<String> Scores = new ArrayList<>();
	private ArrayList<String> gameweeks = new ArrayList<>();

	public HomeFragment(){}

	private final String myIP = "192.168.1.199";
	//private Championship matches;
	private GameWeek matchList;
	private TextView text1, text3;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeStatsManagerBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textView;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;

		//Get the view
		View v = inflater.inflate(R.layout.fragment_home_stats_manager,null);

		//Get the button to start a game
		Button b = v.findViewById(R.id.watchGame);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Go to the tabbed view for the game
				Intent intent = new Intent(getActivity(), Tabbed_Stats_Manager.class);
				startActivity(intent);
			}
		});

		text1 = v.findViewById(R.id.textView);
		text3 = v.findViewById(R.id.textView3);

		matchList = new GameWeek(myIP);

		text1.setText(matchList.getMatchList(0,true));
		text3.setText(matchList.getMatchList(0,false));

		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}