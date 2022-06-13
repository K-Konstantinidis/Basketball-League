package com.example.esake;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTeamManagementStatsManager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTeamManagementStatsManager extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTeamManagementStatsManager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTeamManagementStatsManager.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTeamManagementStatsManager newInstance(String param1, String param2) {
        FragmentTeamManagementStatsManager fragment = new FragmentTeamManagementStatsManager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		//Get the view
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_team_management_startingteam_stats_manager, null);

		//Find the recyclerView
		RecyclerView recyclerView = root.findViewById(R.id.recViewSubs);
		//Create an adapter
		PlayerAdapter adapter;
		//Create a list for the team ranking
		List<Player> playerList = new ArrayList<>();

		//Set a vertical layout manager
		//If you are in an Activity class pass 'this'. But since
		//we are in a Fragment Class we have to pass getContext()
		recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

		//Make a connection with the database via php
		Connector lr = new Connector(myIP.getIp(), "players");


		//THIS 3 NEEDS FIXING
//		playerList = lr.getPlayer();
//		adapter = new PlayerAdapter(getContext(), playerList);
//		recyclerView.setAdapter(adapter);

		//Get the start game button
		Button startGameBtn = root.findViewById(R.id.startGameButton);

		return root;
    }
}