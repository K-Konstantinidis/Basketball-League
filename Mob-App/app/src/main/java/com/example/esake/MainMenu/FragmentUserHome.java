package com.example.esake.MainMenu;

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

import com.example.esake.Connector;
import com.example.esake.GameWeek;
import com.example.esake.Adapters.HomeUserAdapter;
import com.example.esake.R;
import com.example.esake.MyIP;

import java.util.ArrayList;

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

	private String round_id;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Get the view
		@SuppressLint("InflateParams") View view = inflater.inflate(R.layout.fragment_home_user, null);

		gameweekSpinner = view.findViewById(R.id.gameweek_selection_spinner);

		Connector weeks = new Connector(MyIP.getIp(), "getAllGameweeks.php?cid=1");

		ArrayAdapter<String> adapter;
		try {
			adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, weeks.gameWeeks());
			gameweekSpinner.setAdapter(adapter);

			gameweekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@SuppressLint("SetTextI18n")
				@Override
				public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

					String round = gameweekSpinner.getSelectedItem().toString();
					round_id = round.substring(round.length() - 2);

					//Find the recyclerView
					RecyclerView recyclerView = view.findViewById(R.id.recViewHomeUser);
					//Create an adapter
					HomeUserAdapter adapter1;
					ArrayList<GameWeek> homeUserGamesList;

					recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

					//Make a connection with the database via php
					try {
						Connector cn = new Connector(MyIP.getIp(),"getGameweekMatches.php?cid=1&rid=" + round_id);
						homeUserGamesList = cn.weekMatches();
						adapter1 = new HomeUserAdapter(getContext(), homeUserGamesList, round_id);
						recyclerView.setAdapter(adapter1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				@Override
				public void onNothingSelected(AdapterView<?> parentView) {}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
}