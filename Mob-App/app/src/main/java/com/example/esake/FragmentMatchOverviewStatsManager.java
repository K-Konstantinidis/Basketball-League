package com.example.esake;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMatchOverviewStatsManager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMatchOverviewStatsManager extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

	private Match game;

    public FragmentMatchOverviewStatsManager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMatchOverviewStatsManager.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMatchOverviewStatsManager newInstance(String param1, String param2) {
        FragmentMatchOverviewStatsManager fragment = new FragmentMatchOverviewStatsManager();
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

		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_match_overview_stats_manager, null);
		game = new Match(myIP.getIp());

		TextView team1 = root.findViewById(R.id.homeTeamName_statsManager);
		TextView team2 = root.findViewById(R.id.awayTeamName_statsManager);
		team1.setText(game.getTeamList(2,true));
		team2.setText(game.getTeamList(2,false));

		TextView score1 = root.findViewById(R.id.SHomeScore_statsManager);
		TextView score2 = root.findViewById(R.id.SAwayScore_statsManager);
		score1.setText(game.getScoreList(2, true));
		score2.setText(game.getScoreList(2,false));

    	return root;
    }
}