package com.example.esake.StatsManagerFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.esake.DatabasePHP.Connector;
import com.example.esake.R;

public class FragmentSmGameOverview extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

	private Connector game;

    public FragmentSmGameOverview() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentSmGameOverview newInstance(String param1, String param2) {
        FragmentSmGameOverview fragment = new FragmentSmGameOverview();
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

		@SuppressLint("InflateParams") ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_match_overview_stats_manager, null);

		TextView team1 = root.findViewById(R.id.homeTeamName_statsManager);
		TextView team2 = root.findViewById(R.id.awayTeamName_statsManager);

		TextView totalScore1 = root.findViewById(R.id.SHomeScore_statsManager);
		TextView totalScore2 = root.findViewById(R.id.SAwayScore_statsManager);

		TextView homeTeamQ1 = root.findViewById(R.id.Q1HomeScore_statsManager);
		TextView homeTeamQ2 = root.findViewById(R.id.Q2HomeScore_statsManager);
		TextView homeTeamQ3 = root.findViewById(R.id.Q3HomeScore_statsManager);
		TextView homeTeamQ4 = root.findViewById(R.id.Q4HomeScore_statsManager);

		TextView awayTeamQ1 = root.findViewById(R.id.Q1AwayScore_statsManager);
		TextView awayTeamQ2 = root.findViewById(R.id.Q2AwayScore_statsManager);
		TextView awayTeamQ3 = root.findViewById(R.id.Q3AwayScore_statsManager);
		TextView awayTeamQ4 = root.findViewById(R.id.Q4AwayScore_statsManager);

		return root;
    }
}