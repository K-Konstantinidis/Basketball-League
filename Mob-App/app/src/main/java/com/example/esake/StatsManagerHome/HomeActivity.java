package com.example.esake.StatsManagerHome;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.Connector;
import com.example.esake.GameWeek;
import com.example.esake.R;
import com.example.esake.myIP;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

	private Connector matchList;
	private TextView text1, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_stats_manager);

		//Find the recyclerView
		RecyclerView recyclerView = findViewById(R.id.recViewHomeSM);
		//Create an adapter
		HomeSmAdapter adapter;
		//Create a list for the team ranking
		List<GameWeek> homeSmList = new ArrayList<>();

		//Set a vertical layout manager
		//If you are in an Activity class pass 'this'. But since
		//we are in a Fragment Class we have to pass getContext()
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		//Make a connection with the database via php
		Connector Lr = new Connector(myIP.getIp(),"week-matches");

		homeSmList = Lr.getMatches();

		adapter = new HomeSmAdapter(this, homeSmList, homeSmList.get(0).getGameweek());
		recyclerView.setAdapter(adapter);
    }
}