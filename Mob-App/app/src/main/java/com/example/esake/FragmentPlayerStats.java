package com.example.esake;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPlayerStats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlayerStats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPlayerStats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlayerStats.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlayerStats newInstance(String param1, String param2) {
        FragmentPlayerStats fragment = new FragmentPlayerStats();
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

	private Connector stats;
	private TextView pName, pRating;
	private TextView pPts, pFg;
	private TextView p3Fg, pPercentfg;
	private TextView pReb, pAst;
	private TextView pStl,pBlock;
	private TextView pFls, pTurnover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_player_stats, container, false);

		stats = new Connector(myIP.getIp(), "player-stats");

		pName = view.findViewById(R.id.player_stats_playerName);
		pRating = view.findViewById(R.id.player_stats_rating_value);
		pPts = view.findViewById(R.id.player_stats_pts_value);
		pFg = view.findViewById(R.id.player_stats_fg_value);
		p3Fg = view.findViewById(R.id.player_stats_3fg_value);

		pPercentfg = view.findViewById(R.id.player_stats_percentfg_value);
		pReb = view.findViewById(R.id.player_stats_reb_value);
		pAst = view.findViewById(R.id.player_stats_ast_value);
		pStl = view.findViewById(R.id.player_stats_stl_value);
		pBlock = view.findViewById(R.id.player_stats_block_value);

		pFls = view.findViewById(R.id.player_stats_foul_value);
		pTurnover = view.findViewById(R.id.player_stats_turnover_value);



		return view;
    }
}