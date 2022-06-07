package com.example.esake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class FragmentUserHome extends Fragment {

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;

    public FragmentUserHome() {}

//    public static FragmentUserHome newInstance(String param1, String param2) {
//        FragmentUserHome fragment = new FragmentUserHome();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	//Get the view
    	View v = inflater.inflate(R.layout.fragment_home_user, null);
        // Inflate the layout for this fragment
        // Return inflater.inflate(R.layout.fragment_home_user, container, false);
        //@SuppressLint("InflateParams") ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_user, null);

		// Get the spinner
        Spinner dropdown = (Spinner) v.findViewById(R.id.gameweek_selection);

        // Get the button
        Button game = v.findViewById(R.id.gameweek_preview_gameButton);

        // Create a list of items for the spinner.
        String[] items = new String[]{"Gameweek 1", "Gameweek 2", "Gameweek 3", "etc..."};

        // Create an adapter to describe how the items are displayed, adapters are used in several places in android.
        // There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        // Set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        return v;
    }
}