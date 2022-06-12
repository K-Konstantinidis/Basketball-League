package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTeamStatsLiveUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTeamStatsLiveUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTeamStatsLiveUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTeamStatsLiveUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTeamStatsLiveUser newInstance(String param1, String param2) {
        FragmentTeamStatsLiveUser fragment = new FragmentTeamStatsLiveUser();
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

	private Connector team_finished_stats, team_live_stats;
	private ImageView imgHomeTeam, imgAwayTeam;
	private TextView homeName, awayName;
	private TextView homePts, awayPts;
	private TextView homeFg, awayFg;
	private TextView home2Fg, away2Fg;
	private TextView home3Fg, away3Fg;
	private TextView homePercentft, awayPercentft;
	private TextView homeReb, awayReb;
	private TextView homeAst, awayAst;
	private TextView homeStl, awayStl;
	private TextView homeBlock, awayBlock;
	private TextView homeOffReb, awayOffReb;
	private TextView homeDefReb, awayDefReb;
	private TextView homeFls, awayFls;
	private TextView homeTurnover, awayTurnover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view;

		if(true){
			view = inflater.inflate(R.layout.fragment_team_stats_finished_user, container, false);

			team_finished_stats = new Connector(myIP.getIp(), "team-finished-stats");

//			homeName = view.findViewById(R.id.team_stats_teamName);
			imgHomeTeam = (ImageView) view.findViewById(R.id.imageteam1);
			Picasso.with(getContext()).load(team_finished_stats.getfinishedTeamLogo(0)).fit().into(imgHomeTeam);
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

//			homeName.setText(team_finished_stats.getfinishedTeamName(0));
			homePts.setText(team_finished_stats.getfinishedTeamTotal_points(0));
			homeFg.setText(team_finished_stats.getfinishedTeamShots_made(0));
			home2Fg.setText(team_finished_stats.getfinishedTeamPerc_2_in(0));
			home3Fg.setText(team_finished_stats.getfinishedTeamPerc_3_in(0));

			homePercentft.setText(team_finished_stats.getfinishedTeamPerc_freethrows_in(0));
			homeOffReb.setText(team_finished_stats.getfinishedTeamTotal_offensive_rebounds(0));
			homeDefReb.setText(team_finished_stats.getfinishedTeamTotal_defensive_rebounds(0));
			homeReb.setText(team_finished_stats.getfinishedTeamTotal_rebounds(0));

			homeAst.setText(team_finished_stats.getfinishedTeamTotal_assists(0));
			homeStl.setText(team_finished_stats.getfinishedTeamTotal_steals(0));
			homeBlock.setText(team_finished_stats.getfinishedTeamTotal_blocks(0));

			homeFls.setText(team_finished_stats.getfinishedTeamTotal_fouls(0));
			homeTurnover.setText(team_finished_stats.getfinishedTeamTotal_turnovers(0));

			//awayName = view.findViewById(R.id.team_stats_teamName);
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

//			awayName.setText(team_finished_stats.getfinishedTeamName(0));
			imgAwayTeam = (ImageView) view.findViewById(R.id.imageteam2);
			Picasso.with(getContext()).load(team_finished_stats.getfinishedTeamLogo(1)).fit().into(imgAwayTeam);

			awayPts.setText(team_finished_stats.getfinishedTeamTotal_points(1));
			awayFg.setText(team_finished_stats.getfinishedTeamShots_made(1));
			away2Fg.setText(team_finished_stats.getfinishedTeamPerc_2_in(1));
			away3Fg.setText(team_finished_stats.getfinishedTeamPerc_3_in(1));

			awayPercentft.setText(team_finished_stats.getfinishedTeamPerc_freethrows_in(1));
			awayOffReb.setText(team_finished_stats.getfinishedTeamTotal_offensive_rebounds(1));
			awayDefReb.setText(team_finished_stats.getfinishedTeamTotal_defensive_rebounds(1));
			awayReb.setText(team_finished_stats.getfinishedTeamTotal_rebounds(1));

			awayAst.setText(team_finished_stats.getfinishedTeamTotal_assists(1));
			awayStl.setText(team_finished_stats.getfinishedTeamTotal_steals(1));
			awayBlock.setText(team_finished_stats.getfinishedTeamTotal_blocks(1));

			awayFls.setText(team_finished_stats.getfinishedTeamTotal_fouls(1));
			awayTurnover.setText(team_finished_stats.getfinishedTeamTotal_turnovers(1));

		}
		else{
			view = inflater.inflate(R.layout.fragment_team_stats_live_user, container, false);
		}
		return view;
    }
}