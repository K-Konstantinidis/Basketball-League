package com.example.esake;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentMatchOverviewUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMatchOverviewUser extends Fragment {

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	private static final String ARG_PARAM3 = "param3";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	private String mParam3;

	public FragmentMatchOverviewUser() {
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
	public static FragmentMatchOverviewUser newInstance(String param1, String param2, String param3) {
		FragmentMatchOverviewUser fragment = new FragmentMatchOverviewUser();
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

	private Connector userOverViewFinished;
	private TextView homeName, awayName;
	private TextView scoreHome, scoreAway;
	private TextView Q1Home, Q1Away;
	private TextView Q2Home, Q2Away;
	private TextView Q3Home, Q3Away;
	private TextView Q4Home, Q4Away;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view;

		if(Integer.parseInt(mParam2)==0) {
			view = inflater.inflate(R.layout.fragment_match_overview_finished_user, container, false);

			String url = "getMatchDetailedScore.php?lang=gr&cid=1&rid="+mParam1+"&gid="+mParam3;
			userOverViewFinished = new Connector(myIP.getIp(),"overview-stats",url);

			homeName = view.findViewById(R.id.homeTeamName_user);
			awayName = view.findViewById(R.id.awayTeamName_user);;

			scoreHome = view.findViewById(R.id.SHomeScore_user);
			scoreHome.setTextColor(Color.BLACK);
			scoreAway = view.findViewById(R.id.SAwayScore_user);
			scoreAway.setTextColor(Color.BLACK);

			Q1Home = view.findViewById(R.id.Q1HomeScore_user);
			Q2Home = view.findViewById(R.id.Q2HomeScore_user);
			Q3Home = view.findViewById(R.id.Q3HomeScore_user);
			Q4Home = view.findViewById(R.id.Q4HomeScore_user);

			Q1Away = view.findViewById(R.id.Q1AwayScore_user);
			Q2Away = view.findViewById(R.id.Q2AwayScore_user);
			Q3Away = view.findViewById(R.id.Q3AwayScore_user);
			Q4Away = view.findViewById(R.id.Q4AwayScore_user);

			homeName.setText(userOverViewFinished.getOverViewFinishedGame().getHomeTeamName());
			awayName.setText(userOverViewFinished.getOverViewFinishedGame().getAwayTeamName());

			scoreHome.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getScore1()));
			scoreAway.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getScore2()));
			Q1Home.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ1score1()));
			Q2Home.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ2score1()));
			Q3Home.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ3score1()));
			Q4Home.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ4score1()));
			Q4Home.setTextColor(Color.BLACK);

			Q1Away.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ1score2()));
			Q2Away.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ2score2()));
			Q3Away.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ3score2()));
			Q4Away.setText(String.valueOf(userOverViewFinished.getOverViewFinishedGame().getQ4score2()));
			Q4Away.setTextColor(Color.BLACK);

		}
		else{
			view = inflater.inflate(R.layout.fragment_match_overview_user, container, false);
		}
		 return view;
    }
}