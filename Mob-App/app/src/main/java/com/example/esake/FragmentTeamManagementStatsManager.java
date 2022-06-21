package com.example.esake;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.esake.ui.tabbedView_statsManager.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentTeamManagementStatsManager extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentTeamManagementStatsManager() {
        // Required empty public constructor
    }

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

    private Connector homeTeamPlayerConnector;
    private List<Player> allHomeTeamPlayers = new ArrayList<>();
	private Player[] selectedHomeTeamPlayers = new Player[]{null,null,null,null,null};
	private Player[] selectedHomeTeamSubstitutes = new Player[]{null,null,null,null,null,null,null};

	private Connector awayTeamPlayerConnector;
	private List<Player> allAwayTeamPlayers = new ArrayList<>();
	private Player[] selectedAwayTeamPlayers = new Player[]{null,null,null,null,null};
	private Player[] selectedAwayTeamSubstitutes = new Player[]{null,null,null,null,null,null,null};

	private Spinner[] homeTeamSpinners, awayTeamSpinners, homeTeamSubsSpinners, awayTeamSubsSpinners;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		//Get the view
		View root = inflater.inflate(R.layout.fragment_team_management_startingteam_stats_manager, null);

		homeTeamPlayerConnector = new Connector(myIP.getIp(),"players", mParam1);
		awayTeamPlayerConnector = new Connector(myIP.getIp(),"players", mParam2);

		homeTeamSpinners = new Spinner[]{
			(Spinner) root.findViewById(R.id.spinner1_1),
			(Spinner) root.findViewById(R.id.spinner1_2),
			(Spinner) root.findViewById(R.id.spinner1_3),
			(Spinner) root.findViewById(R.id.spinner1_4),
			(Spinner) root.findViewById(R.id.spinner1_5) };

		awayTeamSpinners = new Spinner[]{
			(Spinner) root.findViewById(R.id.spinner2_1),
			(Spinner) root.findViewById(R.id.spinner2_2),
			(Spinner) root.findViewById(R.id.spinner2_3),
			(Spinner) root.findViewById(R.id.spinner2_4),
			(Spinner) root.findViewById(R.id.spinner2_5) };

		homeTeamSubsSpinners = new Spinner[]{
			(Spinner) root.findViewById(R.id.spinner1_6),
			(Spinner) root.findViewById(R.id.spinner1_7),
			(Spinner) root.findViewById(R.id.spinner1_8),
			(Spinner) root.findViewById(R.id.spinner1_9),
			(Spinner) root.findViewById(R.id.spinner1_10),
			(Spinner) root.findViewById(R.id.spinner1_11),
			(Spinner) root.findViewById(R.id.spinner1_12) };

		awayTeamSubsSpinners = new Spinner[]{
			(Spinner) root.findViewById(R.id.spinner2_6),
			(Spinner) root.findViewById(R.id.spinner2_7),
			(Spinner) root.findViewById(R.id.spinner2_8),
			(Spinner) root.findViewById(R.id.spinner2_9),
			(Spinner) root.findViewById(R.id.spinner2_10),
			(Spinner) root.findViewById(R.id.spinner2_11),
			(Spinner) root.findViewById(R.id.spinner2_12) };

		allHomeTeamPlayers.add(0,new Player("",-1,-1));
		allHomeTeamPlayers.addAll(homeTeamPlayerConnector.getTeamPlayers(0));

		PlayerSpinnerAdapter homeTeamPlayersAdapter = new PlayerSpinnerAdapter(getContext(),
			android.R.layout.simple_spinner_dropdown_item, allHomeTeamPlayers);

		setAdapter(homeTeamSpinners, homeTeamPlayersAdapter, selectedHomeTeamPlayers);

		PlayerSpinnerAdapter homeTeamSubsAdapter = new PlayerSpinnerAdapter(getContext(),
			android.R.layout.simple_spinner_dropdown_item, allHomeTeamPlayers);

		setAdapter(homeTeamSubsSpinners, homeTeamSubsAdapter, selectedHomeTeamSubstitutes);

		//Away team
		allAwayTeamPlayers.add(0,new Player("",-1,-1));
		allAwayTeamPlayers.addAll(awayTeamPlayerConnector.getTeamPlayers(1));

		PlayerSpinnerAdapter awayTeamPlayersAdapter = new PlayerSpinnerAdapter(getContext(),
			android.R.layout.simple_spinner_dropdown_item, allAwayTeamPlayers);

		setAdapter(awayTeamSpinners, awayTeamPlayersAdapter, selectedAwayTeamPlayers);

		PlayerSpinnerAdapter awayTeamSubsAdapter = new PlayerSpinnerAdapter(getContext(),
			android.R.layout.simple_spinner_dropdown_item, allAwayTeamPlayers);

		setAdapter(awayTeamSubsSpinners, awayTeamSubsAdapter, selectedAwayTeamSubstitutes);

		//Get the start game button
		Button startGameBtn = root.findViewById(R.id.startGameButton);

		final FragmentTeamManagementStatsManager currentFragment = this;
		//View f = root.findViewById(R.id.scrollView_start_lineup_statsManager);

		startGameBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (selectedTeamPlayersContainsEmptyElement(selectedHomeTeamPlayers)
				|| selectedTeamPlayersContainsEmptyElement(selectedAwayTeamPlayers)
				|| selectedTeamPlayersContainsEmptyElement(selectedHomeTeamSubstitutes)
					|| selectedTeamPlayersContainsEmptyElement(selectedAwayTeamSubstitutes)) {
					Toast.makeText(getContext(),"Cannot start match with empty positions!",Toast.LENGTH_SHORT).show();
					return;
				}

				boolean allHomeTeamPlayersDifferent = checkForAllUniquePlayers(selectedHomeTeamPlayers, selectedHomeTeamSubstitutes);
				boolean allAwayTeamPlayersDifferent = checkForAllUniquePlayers(selectedAwayTeamPlayers, selectedAwayTeamSubstitutes);

				if (allHomeTeamPlayersDifferent && allAwayTeamPlayersDifferent){
					Toast.makeText(getContext(),"The game will start with the chosen players",Toast.LENGTH_SHORT).show();
					FragmentTeamManagementLiveStatsManager newFragment = FragmentTeamManagementLiveStatsManager.newInstance(selectedHomeTeamPlayers, selectedHomeTeamSubstitutes,
						selectedAwayTeamPlayers, selectedAwayTeamSubstitutes);

					FragmentActivity parentActivity = currentFragment.getActivity();
					parentActivity.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.frameLayout_start_lineup_statsManager, newFragment)
						.commit();
				}
				else
					Toast.makeText(getContext(),"Teams cannot contain duplicate players!",Toast.LENGTH_SHORT).show();
			}
		});

		return root;
    }

	private boolean selectedTeamPlayersContainsEmptyElement(Player[] selectedTeamPlayers) {
    	for (Player p:selectedTeamPlayers) {
			if (p==null || p.getNumber()==-1) return true;
		}
		return false;
    }

	private void setAdapter(Spinner[] teamSpinners, PlayerSpinnerAdapter teamPlayersAdapter, Player[] selectedTeamPlayers) {

		for (int i = 0; i < teamSpinners.length; i++) {
			teamSpinners[i].setAdapter(teamPlayersAdapter);
			int finalI = i;
			teamSpinners[i].setSelection(0,true);
			teamSpinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
					Player chosenPlayer = teamPlayersAdapter.getItem(position);
					selectedTeamPlayers[finalI] = chosenPlayer;

					if (position == 0)
						Toast.makeText(getContext(), "BLANK PLAYER", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onNothingSelected(AdapterView<?> adapterView) {

				}
			});
		}
	}

	private boolean checkForAllUniquePlayers(Player[] selectedTeamPlayers, Player[] selectedTeamSubs) {
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
	}
}