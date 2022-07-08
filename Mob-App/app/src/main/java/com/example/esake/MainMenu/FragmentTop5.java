package com.example.esake.MainMenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.Connector;
import com.example.esake.R;
import com.example.esake.Top5;
import com.example.esake.Adapters.Top5Adapter;
import com.example.esake.MyIP;

import java.util.ArrayList;

public class FragmentTop5 extends Fragment implements AdapterView.OnItemSelectedListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentTop5() {
        // Required empty public constructor
    }

    public static FragmentTop5 newInstance(String param1, String param2) {
        FragmentTop5 fragment = new FragmentTop5();
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
        @SuppressLint("InflateParams") ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_top5, null);

		Connector weeks = new Connector(MyIP.getIp(),"getEligibleTop5Rounds.php?lang=gr&cid=1&rid="+null);
        //Get the spinner from the xml.
        Spinner dropdown = view.findViewById(R.id.spinner);

		ArrayAdapter<String> spinnerAdapter;
		try {
			spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, weeks.gameWeeks());
			dropdown.setAdapter(spinnerAdapter);

			dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@SuppressLint("SetTextI18n")
				@Override
				public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

					String round = dropdown.getSelectedItem().toString();
					round_id = round.substring(round.length() - 2);

					//Find the recyclerView
					RecyclerView recyclerView = view.findViewById(R.id.recViewTop5);
					//Create an adapter
					Top5Adapter adapter2;

					//Create a list for the team ranking
					ArrayList<Top5> top5List;

					//Set a vertical layout manager
					//If you are in an Activity class pass 'this'. But since
					//we are in a Fragment Class we have to pass getContext()
					recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

					//Make a connection with the database via php
					try {
						Connector lr = new Connector(MyIP.getIp(),"getRoundTop5.php?lang=gr&cid=1&rid="+round_id);
						top5List = lr.top5();
						adapter2 = new Top5Adapter(getContext(), top5List);
						recyclerView.setAdapter(adapter2);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Show selected spinner item
        Toast.makeText(parent.getContext(), "You selected: ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}