package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentLeagueTable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLeagueTable extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	//Variables for set Text and other
	private TextView team_name,MatchesPlayed,Points,Wins,Losses;
	private ImageView logo_path;
	private Connector Lr;

    public FragmentLeagueTable() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentLeagueTable.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLeagueTable newInstance(String param1, String param2) {
        FragmentLeagueTable fragment = new FragmentLeagueTable();
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

    // IDs to Variables
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

		View v =  inflater.inflate(R.layout.fragment_league_table, container, false);

		logo_path = (ImageView) v.findViewById(R.id.league_table_team_logo);
		team_name = v.findViewById(R.id.league_table_teamName);
		MatchesPlayed=v.findViewById(R.id.league_table_games_value);
		Points=v.findViewById(R.id.league_table_points_value);
		Wins=v.findViewById(R.id.league_table_wins_value);
		Losses=	v.findViewById(R.id.league_table_losses_value);

		Lr = new Connector(myIP.getIp(),"league");

		//logo_path.setText(Lr.getLeagueRankTeamLogo(0));
		logo_path.setImageResource(R.drawable.five);
		team_name.setText(Lr.getLeagueRankName(0));
		MatchesPlayed.setText(String.valueOf(Lr.getLeagueRankMatchesPlayed(0)));
		Points.setText(String.valueOf(Lr.getLeagueRankPoints(0)));
		Wins.setText(String.valueOf(Lr.getLeagueRankWins(0)));
		Losses.setText(String.valueOf(Lr.getLeagueRankLosses(0)));

		return v;
    }
}