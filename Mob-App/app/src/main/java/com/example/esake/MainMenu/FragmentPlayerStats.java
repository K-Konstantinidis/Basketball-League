package com.example.esake.MainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.Connector;
import com.example.esake.PlayerStats;
import com.example.esake.Adapters.PlayerStatsAdapter;
import com.example.esake.R;
import com.example.esake.MyIP;

import java.util.List;

public class FragmentPlayerStats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

	public FragmentPlayerStats() {
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
		View v = inflater.inflate(R.layout.fragment_player_stats, container, false);

		//Find the recyclerView
		RecyclerView recyclerView = v.findViewById(R.id.recViewHomePlayerStats);
		//Create an adapter
		PlayerStatsAdapter adapter;
		//Create a list for the team ranking
		List<PlayerStats> playerStatsList;

		//Set a vertical layout manager
		//If you are in an Activity class pass 'this'. But since
		//we are in a Fragment Class we have to pass getContext()
		recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

		//Make a connection with the database via php
		try {
			Connector lr = new Connector(MyIP.getIp(), "getChampionshipPlayerStats.php?lang=gr&cid=1");
			playerStatsList = lr.playerStats();
			adapter = new PlayerStatsAdapter(getContext(), playerStatsList);
			recyclerView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return v;
    }
}