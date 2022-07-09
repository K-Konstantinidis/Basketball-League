package com.example.esake.UserFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.DatabasePHP.Connector;
import com.example.esake.PlayerStats;
import com.example.esake.Adapters.PlayerStatsFinishedAdapter;
import com.example.esake.Adapters.PlayerStatsLiveAdapter;
import com.example.esake.R;
import com.example.esake.DatabasePHP.MyIP;

import java.util.ArrayList;

public class FragmentUserPlayerStatsFinished_Live extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
	private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
	private String mParam3;

    public FragmentUserPlayerStatsFinished_Live() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentUserPlayerStatsFinished_Live newInstance(String param1, String param2, String param3) {
        FragmentUserPlayerStatsFinished_Live fragment = new FragmentUserPlayerStatsFinished_Live();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
		args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
			mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

	private Connector live_stats, finished_stats;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view;

		//true will change
		//We will have a flag from database to
		//find out if the game has finished or not
		//==0 for finished
		if(Integer.parseInt(mParam2)==0){
			 view = inflater.inflate(R.layout.fragment_player_stats_finished_user, container, false);

			//Find the recyclerView
			RecyclerView recyclerView = view.findViewById(R.id.recViewHomePlayerStatsFinished);
			//Create an adapter
			PlayerStatsFinishedAdapter adapter;
			//Create a list for the team ranking
			ArrayList<PlayerStats> playerStatsList;

			//Set a vertical layout manager
			//If you are in an Activity class pass 'this'. But since
			//we are in a Fragment Class we have to pass getContext()
			recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

			//Make a connection with the database via php
			try {
				finished_stats = new Connector(MyIP.getIp(), "getFinishedMatchPlayerStats.php?lang=gr&cid=1&rid=" + mParam1+ "&gid=" + mParam3);
				playerStatsList = finished_stats.finishedPlayerStats();
				adapter = new PlayerStatsFinishedAdapter(getContext(), playerStatsList);
				recyclerView.setAdapter(adapter);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		//live here
		else {
			view = inflater.inflate(R.layout.fragment_player_stats_live_user, container, false);

			//Find the recyclerView
			RecyclerView recyclerView = view.findViewById(R.id.recViewHomePlayerStatsLive);
			//Create an adapter
			PlayerStatsLiveAdapter adapter;
			//Create a list for the team ranking
			ArrayList<PlayerStats> playerStatsList;

			//Set a vertical layout manager
			//If you are in an Activity class pass 'this'. But since
			//we are in a Fragment Class we have to pass getContext()
			recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

			//Make a connection with the database via php
			try {
				live_stats = new Connector(MyIP.getIp(),"getOngoingMatchPlayerStats.php?lang=gr&cid=1&rid=" + mParam1+ "&gid=" + mParam3);
				playerStatsList = live_stats.livePlayerStats();
				adapter = new PlayerStatsLiveAdapter(getContext(), playerStatsList);
				recyclerView.setAdapter(adapter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//The 2 lines below will be deleted after we fix the method in the Connector
			view = inflater.inflate(R.layout.fragment_player_stats_live_user, container, false);
			live_stats = new Connector(MyIP.getIp(),null);

		}
		return view;
    }
}