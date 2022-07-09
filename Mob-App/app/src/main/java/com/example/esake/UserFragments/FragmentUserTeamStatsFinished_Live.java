package com.example.esake.UserFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.esake.DatabasePHP.Connector;
import com.example.esake.R;
import com.example.esake.TeamStats;
import com.example.esake.DatabasePHP.MyIP;
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

	private Connector cn, team_live_stats;
	private ArrayList<TeamStats> teamFnStats = new ArrayList<>();
	private ImageView imgHomeTeam, imgAwayTeam;
	private TextView homeName, awayName,homePts, awayPts, homeFg, awayFg, home2Fg, away2Fg, home3Fg, away3Fg,
		homePercentFt, awayPercentFt, homeReb, awayReb, homeAst, awayAst, homeStl, awayStl, homeBlk, awayBlk,
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
			homePercentFt = view.findViewById(R.id.freeThrowsHome);
			homeReb = view.findViewById(R.id.totalReboundsHome);
			homeOffReb = view.findViewById(R.id.offensiveReboundsHome);
			homeDefReb = view.findViewById(R.id.defensiveReboundsHome);
			homeAst = view.findViewById(R.id.assistsHome);
			homeStl = view.findViewById(R.id.stealsHome);
			homeBlk = view.findViewById(R.id.blocksHome);
			homeFls = view.findViewById(R.id.foulsHome);
			homeTurnover = view.findViewById(R.id.turnoverHome);

			//awayName = view.findViewById(R.id.team_stats_teamName);
			imgAwayTeam = view.findViewById(R.id.imageteam2);
			awayPts = view.findViewById(R.id.fieldGoalsAway);
			awayFg = view.findViewById(R.id.fieldGoalsMadeAway);
			away2Fg = view.findViewById(R.id.twoPtsPercentAway);
			away3Fg = view.findViewById(R.id.threePtsPercentAway);
			awayPercentFt = view.findViewById(R.id.freeThrowsLAway);
			awayReb = view.findViewById(R.id.totalReboundsAway);
			awayOffReb = view.findViewById(R.id.offensiveReboundsAway);
			awayDefReb = view.findViewById(R.id.defensiveReboundsAway);
			awayAst = view.findViewById(R.id.assistsAway);
			awayStl = view.findViewById(R.id.stealsAway);
			awayBlk = view.findViewById(R.id.blocksAway);
			awayFls = view.findViewById(R.id.foulsAway);
			awayTurnover = view.findViewById(R.id.turnoversAway);

			try {
				cn = new Connector(MyIP.getIp(), url);
				teamFnStats = cn.teamFinishedStats();

				//homeName.setText(team_finished_stats.getfinishedTeamName(0));
				Picasso.with(getContext()).load(teamFnStats.get(0).getLogo()).fit().into(imgHomeTeam);
				homePts.setText(teamFnStats.get(0).getIntTotal_points());
				homeFg.setText(teamFnStats.get(0).getIntShots_made());
				home2Fg.setText(teamFnStats.get(0).getPerc_2_in());
				home3Fg.setText(teamFnStats.get(0).getPerc_3_in());
				homePercentFt.setText(teamFnStats.get(0).getPerc_freethrows_in());
				homeOffReb.setText(teamFnStats.get(0).getIntTotal_offensive_rebounds());
				homeDefReb.setText(teamFnStats.get(0).getIntTotal_defensive_rebounds());
				homeReb.setText(teamFnStats.get(0).getIntTotal_rebounds());
				homeAst.setText(teamFnStats.get(0).getIntTotal_assists());
				homeStl.setText(teamFnStats.get(0).getIntTotal_steals());
				homeBlk.setText(teamFnStats.get(0).getIntTotal_blocks());
				homeFls.setText(teamFnStats.get(0).getIntTotal_fouls());
				homeTurnover.setText(teamFnStats.get(0).getIntTotal_turnovers());

				//awayName.setText(team_finished_stats.getfinishedTeamName(0));
				Picasso.with(getContext()).load(teamFnStats.get(1).getLogo()).fit().into(imgAwayTeam);
				awayPts.setText(teamFnStats.get(1).getIntTotal_points());
				awayFg.setText(teamFnStats.get(1).getIntShots_made());
				away2Fg.setText(teamFnStats.get(1).getPerc_2_in());
				away3Fg.setText(teamFnStats.get(1).getPerc_3_in());
				awayPercentFt.setText(teamFnStats.get(1).getPerc_freethrows_in());
				awayOffReb.setText(teamFnStats.get(1).getIntTotal_offensive_rebounds());
				awayDefReb.setText(teamFnStats.get(1).getIntTotal_defensive_rebounds());
				awayReb.setText(teamFnStats.get(1).getIntTotal_rebounds());
				awayAst.setText(teamFnStats.get(1).getIntTotal_assists());
				awayStl.setText(teamFnStats.get(1).getIntTotal_steals());
				awayBlk.setText(teamFnStats.get(1).getIntTotal_blocks());
				awayFls.setText(teamFnStats.get(1).getIntTotal_fouls());
				awayTurnover.setText(teamFnStats.get(1).getIntTotal_turnovers());
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