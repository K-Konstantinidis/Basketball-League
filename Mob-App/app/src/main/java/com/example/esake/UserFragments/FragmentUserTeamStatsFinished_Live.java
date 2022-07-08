package com.example.esake.UserFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.esake.Connector;
import com.example.esake.R;
import com.example.esake.TeamStats;
import com.example.esake.MyIP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentUserTeamStatsFinished_Live extends Fragment {

	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String ARG_PARAM3 = "param3";

	private String mParam1;
	private String mParam2;
	private String mParam3;

	public FragmentUserTeamStatsFinished_Live() {
		// Required empty public constructor
	}

	public static FragmentUserTeamStatsFinished_Live newInstance(String param1, String param2, String param3) {
		FragmentUserTeamStatsFinished_Live fragment = new FragmentUserTeamStatsFinished_Live();
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

	private Connector teamFnStatsCn, team_live_stats;
	private ArrayList<TeamStats> teamFnStats = new ArrayList<>();
	private ImageView imgHomeTeam, imgAwayTeam;
	private TextView homeName, awayName,homePts, awayPts, homeFg, awayFg, home2Fg, away2Fg, home3Fg, away3Fg,
		homePercentft, awayPercentft, homeReb, awayReb, homeAst, awayAst, homeStl, awayStl, homeBlock, awayBlock,
		homeOffReb, awayOffReb, homeDefReb, awayDefReb, homeFls, awayFls, homeTurnover, awayTurnover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view;

		//== 0 here for finished
		if(Integer.parseInt(mParam2)==0){
			view = inflater.inflate(R.layout.fragment_team_stats_finished_user, container, false);

			String url = "getFinishedMatchTeamStats.php?lang=gr&cid=1&rid=" + mParam1+ "&gid=" + mParam3;

			//homeName = view.findViewById(R.id.team_stats_teamName);
			imgHomeTeam = view.findViewById(R.id.imageteam1);
			homePts = view.findViewById(R.id.fieldGoalsHome);
			homeFg = view.findViewById(R.id.fieldGoalsMadeHome);
			home2Fg = view.findViewById(R.id.twoPtsPercentHome);
			home3Fg = view.findViewById(R.id.threePtsPercentHome);
			homePercentft = view.findViewById(R.id.freeThrowsHome);
			homeReb = view.findViewById(R.id.totalReboundsHome);
			homeOffReb = view.findViewById(R.id.offensiveReboundsHome);
			homeDefReb = view.findViewById(R.id.defensiveReboundsHome);
			homeAst = view.findViewById(R.id.assistsHome);
			homeStl = view.findViewById(R.id.stealsHome);
			homeBlock = view.findViewById(R.id.blocksHome);
			homeFls = view.findViewById(R.id.foulsHome);
			homeTurnover = view.findViewById(R.id.turnoverHome);

			//awayName = view.findViewById(R.id.team_stats_teamName);
			imgAwayTeam = view.findViewById(R.id.imageteam2);
			awayPts = view.findViewById(R.id.fieldGoalsAway);
			awayFg = view.findViewById(R.id.fieldGoalsMadeAway);
			away2Fg = view.findViewById(R.id.twoPtsPercentAway);
			away3Fg = view.findViewById(R.id.threePtsPercentAway);
			awayPercentft = view.findViewById(R.id.freeThrowsLAway);
			awayReb = view.findViewById(R.id.totalReboundsAway);
			awayOffReb = view.findViewById(R.id.offensiveReboundsAway);
			awayDefReb = view.findViewById(R.id.defensiveReboundsAway);
			awayAst = view.findViewById(R.id.assistsAway);
			awayStl = view.findViewById(R.id.stealsAway);
			awayBlock = view.findViewById(R.id.blocksAway);
			awayFls = view.findViewById(R.id.foulsAway);
			awayTurnover = view.findViewById(R.id.turnoversAway);

			try {
				teamFnStatsCn = new Connector(MyIP.getIp(), url);
				teamFnStats = teamFnStatsCn.teamFinishedStats();

				//homeName.setText(team_finished_stats.getfinishedTeamName(0));
				Picasso.with(getContext()).load(teamFnStatsCn.getfinishedTeamLogo(teamFnStats, 0)).fit().into(imgHomeTeam);
				homePts.setText(teamFnStatsCn.getfinishedTeamTotal_points(teamFnStats, 0));
				homeFg.setText(teamFnStatsCn.getfinishedTeamShots_made(teamFnStats,0));
				home2Fg.setText(teamFnStatsCn.getfinishedTeamPerc_2_in(teamFnStats,0));
				home3Fg.setText(teamFnStatsCn.getfinishedTeamPerc_3_in(teamFnStats,0));
				homePercentft.setText(teamFnStatsCn.getfinishedTeamPerc_freethrows_in(teamFnStats,0));
				homeOffReb.setText(teamFnStatsCn.getfinishedTeamTotal_offensive_rebounds(teamFnStats,0));
				homeDefReb.setText(teamFnStatsCn.getfinishedTeamTotal_defensive_rebounds(teamFnStats,0));
				homeReb.setText(teamFnStatsCn.getfinishedTeamTotal_rebounds(teamFnStats,0));
				homeAst.setText(teamFnStatsCn.getfinishedTeamTotal_assists(teamFnStats,0));
				homeStl.setText(teamFnStatsCn.getfinishedTeamTotal_steals(teamFnStats,0));
				homeBlock.setText(teamFnStatsCn.getfinishedTeamTotal_blocks(teamFnStats,0));
				homeFls.setText(teamFnStatsCn.getfinishedTeamTotal_fouls(teamFnStats,0));
				homeTurnover.setText(teamFnStatsCn.getfinishedTeamTotal_turnovers(teamFnStats,0));

				//awayName.setText(team_finished_stats.getfinishedTeamName(0));
				Picasso.with(getContext()).load(teamFnStatsCn.getfinishedTeamLogo(teamFnStats,1)).fit().into(imgAwayTeam);
				awayPts.setText(teamFnStatsCn.getfinishedTeamTotal_points(teamFnStats,1));
				awayFg.setText(teamFnStatsCn.getfinishedTeamShots_made(teamFnStats,1));
				away2Fg.setText(teamFnStatsCn.getfinishedTeamPerc_2_in(teamFnStats,1));
				away3Fg.setText(teamFnStatsCn.getfinishedTeamPerc_3_in(teamFnStats,1));
				awayPercentft.setText(teamFnStatsCn.getfinishedTeamPerc_freethrows_in(teamFnStats,1));
				awayOffReb.setText(teamFnStatsCn.getfinishedTeamTotal_offensive_rebounds(teamFnStats,1));
				awayDefReb.setText(teamFnStatsCn.getfinishedTeamTotal_defensive_rebounds(teamFnStats,1));
				awayReb.setText(teamFnStatsCn.getfinishedTeamTotal_rebounds(teamFnStats,1));
				awayAst.setText(teamFnStatsCn.getfinishedTeamTotal_assists(teamFnStats,1));
				awayStl.setText(teamFnStatsCn.getfinishedTeamTotal_steals(teamFnStats,1));
				awayBlock.setText(teamFnStatsCn.getfinishedTeamTotal_blocks(teamFnStats,1));
				awayFls.setText(teamFnStatsCn.getfinishedTeamTotal_fouls(teamFnStats,1));
				awayTurnover.setText(teamFnStatsCn.getfinishedTeamTotal_turnovers(teamFnStats,1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//live
		else{
			view = inflater.inflate(R.layout.fragment_team_stats_live_user, container, false);
		}
		return view;
    }
}