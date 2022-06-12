package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentLeagueTable extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

	private Connector Lr;

    public FragmentLeagueTable() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
			// TODO: Rename and change types of parameters
			String mParam1 = getArguments().getString(ARG_PARAM1);
			String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		View v =  inflater.inflate(R.layout.fragment_league_table, container, false);

		//Find the recyclerView
		RecyclerView recyclerView = v.findViewById(R.id.recView);
		//Create an adapter
		LeagueRankAdapter adapter;
		//Create a list for the team ranking
		List<LeagueRank> leagueRankList = new ArrayList<>();

		//Set a vertical layout manager
		//If you are in an Activity class pass 'this'. But since
		//we are in a Fragment Class we have to pass getContext()
		recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

		//Make a connection with the database via php
		Lr = new Connector(myIP.getIp(),"league");

		leagueRankList = Lr.getRanking();

		adapter = new LeagueRankAdapter(getContext(), leagueRankList);
		recyclerView.setAdapter(adapter);

		return v;
    }
}