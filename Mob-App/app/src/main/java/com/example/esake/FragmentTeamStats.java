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

public class FragmentTeamStats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

	public FragmentTeamStats() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View v= inflater.inflate(R.layout.fragment_team_stats, container, false);

		//Find the recyclerView
		RecyclerView recyclerView = v.findViewById(R.id.recViewTeamStats);
		//Create an adapter
		TeamStatsAdapter adapter;
		//Create a list for the team ranking
		List<TeamStats> teamStatsList = new ArrayList<>();

		//Set a vertical layout manager
		//If you are in an Activity class pass 'this'. But since
		//we are in a Fragment Class we have to pass getContext()
		recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

		//Make a connection with the database via php
		Connector lr = new Connector(myIP.getIp(), "team-stats");

		teamStatsList = lr.getTeamStats();

		adapter = new TeamStatsAdapter(getContext(), teamStatsList);
		recyclerView.setAdapter(adapter);

		return v;
    }
}