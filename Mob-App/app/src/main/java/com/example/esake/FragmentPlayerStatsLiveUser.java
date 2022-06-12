package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPlayerStatsLiveUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlayerStatsLiveUser extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPlayerStatsLiveUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlayerStatsLiveUser.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlayerStatsLiveUser newInstance(String param1, String param2) {
        FragmentPlayerStatsLiveUser fragment = new FragmentPlayerStatsLiveUser();
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

	private Connector live_stats, finished_stats;
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
		View view;

		if(true){
			 view = inflater.inflate(R.layout.fragment_player_stats_finished_user, container, false);

			finished_stats = new Connector(myIP.getIp(), "player-finished-stats");

			pName = view.findViewById(R.id.player_stats_live_playerName);
			pRating = view.findViewById(R.id.player_stats_live_rating_value);
			pPts = view.findViewById(R.id.player_stats_live_pts_value);
			pFg = view.findViewById(R.id.player_stats_live_fg_value);
			p3Fg = view.findViewById(R.id.player_stats_live_3fg_value);

			pPercentfg = view.findViewById(R.id.player_stats_live_percentfg_value);
			pReb = view.findViewById(R.id.player_stats_live_reb_value);
			pAst = view.findViewById(R.id.player_stats_live_ast_value);
			pStl = view.findViewById(R.id.player_stats_live_stl_value);
			pBlock = view.findViewById(R.id.player_stats_live_block_value);

			pFls = view.findViewById(R.id.player_stats_live_foul_value);
			pTurnover = view.findViewById(R.id.player_stats_live_turnover_value);

			pName.setText(finished_stats.getfinishedPlayerSurname(0));
			pRating.setText(finished_stats.getfinishedPlayerRating(0));
			pPts.setText(finished_stats.getfinishedPlayerTotal_points(0));
			pFg.setText(finished_stats.getfinishedPlayerShots_made(0));
			p3Fg.setText(finished_stats.getfinishedPlayerPerc_3_in(0));

			pPercentfg.setText(finished_stats.getfinishedPlayerPerc_freethrows_in(0));
			pReb.setText(finished_stats.getfinishedPlayerTotal_rebounds(0));
			pAst.setText(finished_stats.getfinishedPlayerTotal_assists(0));
			pStl.setText(finished_stats.getfinishedPlayerTotal_steals(0));
			pBlock.setText(finished_stats.getfinishedPlayerTotal_blocks(0));

			pFls.setText(finished_stats.getfinishedPlayerTotal_fouls(0));
			pTurnover.setText(finished_stats.getfinishedPlayerTotal_turnovers(0));


		}
		else{
			view = inflater.inflate(R.layout.fragment_player_stats_live_user, container, false);

			live_stats = new Connector(myIP.getIp(), "player-live-stats");

			pName = view.findViewById(R.id.player_stats_live_playerName);
			pRating = view.findViewById(R.id.player_stats_live_rating_value);
			pPts = view.findViewById(R.id.player_stats_live_pts_value);
			pFg = view.findViewById(R.id.player_stats_live_fg_value);
			p3Fg = view.findViewById(R.id.player_stats_live_3fg_value);

			pPercentfg = view.findViewById(R.id.player_stats_live_percentfg_value);
			pReb = view.findViewById(R.id.player_stats_live_reb_value);
			pAst = view.findViewById(R.id.player_stats_live_ast_value);
			pStl = view.findViewById(R.id.player_stats_live_stl_value);
			pBlock = view.findViewById(R.id.player_stats_live_block_value);

			pFls = view.findViewById(R.id.player_stats_live_foul_value);
			pTurnover = view.findViewById(R.id.player_stats_live_turnover_value);
		}




		return view;
    }
}