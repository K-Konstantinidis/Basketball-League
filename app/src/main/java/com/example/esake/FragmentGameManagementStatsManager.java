package com.example.esake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentGameManagementStatsManager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGameManagementStatsManager extends Fragment{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentGameManagementStatsManager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGameManagementStatsManager.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGameManagementStatsManager newInstance(String param1, String param2) {
        FragmentGameManagementStatsManager fragment = new FragmentGameManagementStatsManager();
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

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_game_management_stats_manager, null);
        ImageView imgViewHomeTeam = root.findViewById(R.id.home_team_game_statsmanager);
        ImageView imgViewAwayTeam = root.findViewById(R.id.away_team_game_statsmanager);

        RadioGroup rdg1 = root.findViewById(R.id.radioGroup_homeTeam);
        RadioGroup rdg2 = root.findViewById(R.id.radioGroup_awayTeam);
        ConstraintLayout constraintLayout = root.findViewById(R.id.constraintLayout_games_statsManager);
        ConstraintSet constraintSet = new ConstraintSet();

        imgViewHomeTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdg1.setVisibility(View.VISIBLE);
                rdg2.setVisibility(View.GONE);
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.card_with_actions, ConstraintSet.TOP, R.id.radioGroup_homeTeam, ConstraintSet.BOTTOM,0);
                constraintSet.applyTo(constraintLayout);
            }
        });

        imgViewAwayTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdg2.setVisibility(View.VISIBLE);
                rdg1.setVisibility(View.GONE);
                constraintSet.clone(constraintLayout);
                constraintSet.connect(R.id.card_with_actions, ConstraintSet.TOP, R.id.radioGroup_awayTeam, ConstraintSet.BOTTOM,0);
                constraintSet.applyTo(constraintLayout);
            }
        });

        return root;
    }
}