package com.example.esake;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentTop5 extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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

//    ImageView myImage;
//    private String imageUri;
	private Connector top5;
	private Connector weeks;
	private ImageView imageTop5;
	private TextView nameTop5, posTop5, ratingTop5, pointsTop5;
	private String round_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Get the view
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_top5, null);

		weeks = new Connector(myIP.getIp(),"gameweeks",null);
        //Get the spinner from the xml.
        Spinner dropdown = (Spinner) view.findViewById(R.id.spinner);

		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, weeks.getWeeks());
		dropdown.setAdapter(spinnerAdapter);


		dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@SuppressLint("SetTextI18n")
			@Override
			public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

				String round = dropdown.getSelectedItem().toString();
				round_id = round.substring(round.length() - 2);

				//Find the recyclerView
				RecyclerView recyclerView = view.findViewById(R.id.recViewTop5);
				//Create an adapter

				//Create a list for the team ranking
				List<Top5> top5List = new ArrayList<>();

				Top5Adapter adapter2;

				//Set a vertical layout manager
				//If you are in an Activity class pass 'this'. But since
				//we are in a Fragment Class we have to pass getContext()
				recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

				//Make a connection with the database via php
				Connector lr = new Connector(myIP.getIp(), "top5", round_id); //Tha ginei dynamika gia na allazei to round

				top5List = lr.getTop5();

				adapter2 = new Top5Adapter(getContext(), top5List);
				recyclerView.setAdapter(adapter2);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parentView)
			{}
		});




        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Show selected spinner item
        Toast.makeText(parent.getContext(), "You selected: ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}