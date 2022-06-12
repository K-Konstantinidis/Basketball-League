package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentPlayerStatsLiveUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

	public FragmentPlayerStatsLiveUser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
			String mParam1 = getArguments().getString(ARG_PARAM1);
			String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

	private Connector live_stats, finished_stats;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view;

		//true will change
		//We will have a flag from database to
		//find out if the game has finished or not
		if(true){
			view = inflater.inflate(R.layout.fragment_player_stats_finished_user, container, false);

			//Find the recyclerView
			RecyclerView recyclerView = view.findViewById(R.id.recViewHomePlayerStatsFinished);
			//Create an adapter
			PlayerStatsAdapter adapter;
			//Create a list for the team ranking
			List<PlayerStats> playerStatsList = new ArrayList<>();

			//Set a vertical layout manager
			//If you are in an Activity class pass 'this'. But since
			//we are in a Fragment Class we have to pass getContext()
			recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

			//Make a connection with the database via php
			finished_stats = new Connector(myIP.getIp(), "player-finished-stats");

			playerStatsList = finished_stats.getFinishedPlayerStats();

			adapter = new PlayerStatsAdapter(getContext(), playerStatsList);
			recyclerView.setAdapter(adapter);
		}
		else{
			view = inflater.inflate(R.layout.fragment_player_stats_live_user, container, false);

			live_stats = new Connector(myIP.getIp(), "player-live-stats");

		}
		return view;
    }
}