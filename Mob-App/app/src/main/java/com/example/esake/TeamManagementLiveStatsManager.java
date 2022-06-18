package com.example.esake;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TeamManagementLiveStatsManager extends AppCompatActivity {

	private Connector homeTeamPlayerConnector = new Connector(myIP.getIp(),"players", "2");
	private List<Player> allHomeTeamPlayers = new ArrayList<>();
	private Player[] selectedHomeTeamPlayers = new Player[]{null,null,null,null,null};
	private Player[] selectedHomeTeamSubstitutes = new Player[]{null,null,null,null,null,null,null};

	private Connector awayTeamPlayerConnector = new Connector(myIP.getIp(),"players", "5");
	private List<Player> allAwayTeamPlayers = new ArrayList<>();
	private Player[] selectedAwayTeamPlayers = new Player[]{null,null,null,null,null};
	private Player[] selectedAwayTeamSubstitutes = new Player[]{null,null,null,null,null,null,null};

	private Spinner[] homeTeamSpinners, awayTeamSpinners;

	private ViewGroup newRoot = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_team_management_livegame_stats_manager);

		homeTeamSpinners = new Spinner[]{
			(Spinner) findViewById(R.id.spinnerLive1_1),
			(Spinner) findViewById(R.id.spinnerLive1_2),
			(Spinner) findViewById(R.id.spinnerLive1_3),
			(Spinner) findViewById(R.id.spinnerLive1_4),
			(Spinner) findViewById(R.id.spinnerLive1_5) };

		awayTeamSpinners = new Spinner[]{
			(Spinner) findViewById(R.id.spinnerLive2_1),
			(Spinner) findViewById(R.id.spinnerLive2_2),
			(Spinner) findViewById(R.id.spinnerLive2_3),
			(Spinner) findViewById(R.id.spinnerLive2_4),
			(Spinner) findViewById(R.id.spinnerLive2_5) };

		allHomeTeamPlayers.addAll(homeTeamPlayerConnector.getTeamPlayers(0));

		PlayerSpinnerAdapter homeTeamPlayersAdapter = new PlayerSpinnerAdapter(getApplicationContext(),
			android.R.layout.simple_spinner_dropdown_item, allHomeTeamPlayers);

		setAdapter(homeTeamSpinners, homeTeamPlayersAdapter, selectedHomeTeamPlayers);

		//Away team
		allAwayTeamPlayers.addAll(awayTeamPlayerConnector.getTeamPlayers(1));

		PlayerSpinnerAdapter awayTeamPlayersAdapter = new PlayerSpinnerAdapter(getApplicationContext(),
			android.R.layout.simple_spinner_dropdown_item, allAwayTeamPlayers);

		//myObject = (YourClass) getIntent().getSerializableExtra("KEY_NAME");

		setAdapter(awayTeamSpinners, awayTeamPlayersAdapter, selectedAwayTeamPlayers);
	}
	private void setAdapter(Spinner[] teamSpinners, PlayerSpinnerAdapter teamPlayersAdapter, Player[] selectedTeamPlayers) {

		for (int i = 0; i < teamSpinners.length; i++) {
			teamSpinners[i].setAdapter(teamPlayersAdapter);
			int finalI = i;
			teamSpinners[i].setSelection(0,false);
			teamSpinners[i].setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
					Player chosenPlayer = teamPlayersAdapter.getItem(position);
					selectedTeamPlayers[finalI] = chosenPlayer;
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