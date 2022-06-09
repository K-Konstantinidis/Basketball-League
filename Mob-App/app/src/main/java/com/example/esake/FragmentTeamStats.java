package com.example.esake;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTeamStats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTeamStats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTeamStats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTeamStats.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTeamStats newInstance(String param1, String param2) {
        FragmentTeamStats fragment = new FragmentTeamStats();
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

	private Connector team_stats;
	private TextView tName;
	private TextView tPts, tFg;
	private TextView t3Fg, tPercentfg;
	private TextView tReb, tAst;
	private TextView tStl,tBlock;
	private TextView tFls, tTurnover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_team_stats, container, false);

		team_stats = new Connector(myIP.getIp(), "team-stats");

		tName = view.findViewById(R.id.team_stats_teamName);
		tPts = view.findViewById(R.id.team_stats_pts_value);
		tFg = view.findViewById(R.id.team_stats_fg_value);
		t3Fg = view.findViewById(R.id.team_stats_3fg_value);

		tPercentfg = view.findViewById(R.id.team_stats_percentfg_value);
		tReb = view.findViewById(R.id.team_stats_reb_value);
		tAst = view.findViewById(R.id.team_stats_ast_value);
		tStl = view.findViewById(R.id.team_stats_stl_value);
		tBlock = view.findViewById(R.id.team_stats_block_value);

		tFls = view.findViewById(R.id.team_stats_foul_value);
		tTurnover = view.findViewById(R.id.team_stats_turnover_value);

		return view;
    }
}