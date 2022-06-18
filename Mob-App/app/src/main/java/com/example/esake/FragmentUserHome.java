package com.example.esake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentUserHome extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

	Spinner gameweekSpinner;

	public FragmentUserHome() {}

    public static FragmentUserHome newInstance(String param1, String param2) {
        FragmentUserHome fragment = new FragmentUserHome();
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

	private Connector weeks;
	private String round_id;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Get the view
		View view = inflater.inflate(R.layout.fragment_home_user, null);

		gameweekSpinner = (Spinner) view.findViewById(R.id.gameweek_selection_spinner);

		weeks = new Connector(myIP.getIp(),"gameweeks",null);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, weeks.getWeeks());
		// Set the spinners adapter to the previously created one.
		gameweekSpinner.setAdapter(adapter);

		gameweekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@SuppressLint("SetTextI18n")
			@Override
			public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

				String round = gameweekSpinner.getSelectedItem().toString();
				round_id = round.substring(round.length() - 2);

				RecyclerView recyclerView = view.findViewById(R.id.recViewHomeUser);

				HomeUserAdapter adapter1;
				List<GameWeek> homeUserGamesList = new ArrayList<>();

				recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

				//Make a connection with the database via php
				Connector weekMatches = new Connector(myIP.getIp(),"week-matches",round_id);

				homeUserGamesList = weekMatches.getMatches();

				adapter1 = new HomeUserAdapter(getContext(), homeUserGamesList, round_id);
				recyclerView.setAdapter(adapter1);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parentView)
			{}
		});

		return view;
	}
}