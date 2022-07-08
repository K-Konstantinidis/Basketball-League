package com.example.esake.StatsManagerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.esake.Player;
import com.example.esake.Adapters.PlayerSpinnerAdapter;
import com.example.esake.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentSmTeamManageLive extends Fragment {

	private List<Player> allHomeTeamPlayers = new ArrayList<>();
	private Player[] selectedHomeTeamPlayers = new Player[]{null,null,null,null,null};
	private Player[] selectedHomeTeamSubstitutes = new Player[]{null,null,null,null,null,null,null};

	private List<Player> allAwayTeamPlayers = new ArrayList<>();
	private Player[] selectedAwayTeamPlayers = new Player[]{null,null,null,null,null};
	private Player[] selectedAwayTeamSubstitutes = new Player[]{null,null,null,null,null,null,null};
	private Spinner[] homeTeamSpinners, awayTeamSpinners;


	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String ARG_PARAM3 = "param3";
	private static final String ARG_PARAM4 = "param4";

	private Player[] mParam1;
	private Player[] mParam2;
	private Player[] mParam3;
	private Player[] mParam4;

	public FragmentSmTeamManageLive() {
		// Required empty public constructor
	}

	public static FragmentSmTeamManageLive newInstance(Player[] param1, Player[] param2, Player[] param3, Player[] param4) {
		FragmentSmTeamManageLive fragment = new FragmentSmTeamManageLive();
		Bundle args = new Bundle();
		args.putSerializable(ARG_PARAM1, param1);
		args.putSerializable(ARG_PARAM2, param2);
		args.putSerializable(ARG_PARAM3, param3);
		args.putSerializable(ARG_PARAM4, param4);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = (Player[]) getArguments().getSerializable(ARG_PARAM1);
			mParam2 = (Player[]) getArguments().getSerializable(ARG_PARAM2);
			mParam3 = (Player[]) getArguments().getSerializable(ARG_PARAM3);
			mParam4 = (Player[]) getArguments().getSerializable(ARG_PARAM4);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_team_management_livegame_stats_manager, container, false);

		homeTeamSpinners = new Spinner[]{
			view.findViewById(R.id.spinnerLive1_1),
			view.findViewById(R.id.spinnerLive1_2),
			view.findViewById(R.id.spinnerLive1_3),
			view.findViewById(R.id.spinnerLive1_4),
			view.findViewById(R.id.spinnerLive1_5) };

		awayTeamSpinners = new Spinner[]{
			view.findViewById(R.id.spinnerLive2_1),
			view.findViewById(R.id.spinnerLive2_2),
			view.findViewById(R.id.spinnerLive2_3),
			view.findViewById(R.id.spinnerLive2_4),
			view.findViewById(R.id.spinnerLive2_5) };


		allHomeTeamPlayers.addAll(Arrays.asList(mParam1));
		allHomeTeamPlayers.addAll(Arrays.asList(mParam2));
		selectedHomeTeamPlayers = mParam1;
		selectedHomeTeamSubstitutes = mParam2;

		PlayerSpinnerAdapter homeTeamPlayersAdapter = new PlayerSpinnerAdapter(getContext(),
			android.R.layout.simple_spinner_dropdown_item, allHomeTeamPlayers);

		setAdapter(homeTeamSpinners, homeTeamPlayersAdapter, selectedHomeTeamPlayers);

		//Away team
		allAwayTeamPlayers.addAll(Arrays.asList(mParam3));
		allAwayTeamPlayers.addAll(Arrays.asList(mParam4));

		selectedAwayTeamPlayers = mParam3;
		selectedAwayTeamSubstitutes = mParam4;

		PlayerSpinnerAdapter awayTeamPlayersAdapter = new PlayerSpinnerAdapter(getContext(),
			android.R.layout.simple_spinner_dropdown_item, allAwayTeamPlayers);

		setAdapter(awayTeamSpinners, awayTeamPlayersAdapter, selectedAwayTeamPlayers);

		return view;
	}

	private void setAdapter(Spinner[] teamSpinners, PlayerSpinnerAdapter teamPlayersAdapter, Player[] selectedTeamPlayers) {

		for (int i = 0; i < teamSpinners.length; i++) {
			teamSpinners[i].setAdapter(teamPlayersAdapter);
			int finalI = i;
			teamSpinners[i].setSelection(teamPlayersAdapter.getPosition(selectedTeamPlayers[i]),true);
			teamSpinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
					Player chosenPlayer = teamPlayersAdapter.getItem(position);
					if (selectedTeamPlayers[finalI].equals(chosenPlayer))
						Toast.makeText(getContext(),chosenPlayer.getName() + " is already on the court!",Toast.LENGTH_SHORT).show();
					else {
						Toast.makeText(getContext(),
							chosenPlayer.getName() + " substitutes " + selectedTeamPlayers[finalI].getName(),
							Toast.LENGTH_SHORT).show();
						selectedTeamPlayers[finalI] = chosenPlayer;
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> adapterView) {

				}
			});
		}
	}

	/*private boolean checkForAllUniquePlayers(Player[] selectedTeamPlayers, Player[] selectedTeamSubs) {
		Player[] allSelectedPlayers = new Player[selectedTeamPlayers.length+selectedTeamSubs.length];

		for (int i=0;i<selectedTeamPlayers.length;i++)
			allSelectedPlayers[i] = selectedTeamPlayers[i];

		for (int i=selectedTeamPlayers.length;i<selectedTeamPlayers.length+selectedTeamSubs.length;i++)
			allSelectedPlayers[i] = selectedTeamSubs[i-selectedTeamPlayers.length];

		for (int i=0;i<allSelectedPlayers.length;i++) {
			for (int j = i + 1; j < allSelectedPlayers.length; j++) {
				if (allSelectedPlayers[i].equals(allSelectedPlayers[j])) {
					return false;
				}
			}
		}
		return true;
	}*/
}