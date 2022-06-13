package com.example.esake.StatsManagerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.Connector;
import com.example.esake.GameWeek;
import com.example.esake.HomeUserAdapter;
import com.example.esake.LeagueRank;
import com.example.esake.LeagueRankAdapter;
import com.example.esake.R;
import com.example.esake.Tabbed_Stats_Manager;
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

		adapter = new HomeSmAdapter(this, homeSmList);
		recyclerView.setAdapter(adapter);

//		//Get the button to start a game
//		Button b = findViewById(R.id.watchGame);
//		b.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View v) {
//				//Go to the tabbed view for the game
//				Intent intent = new Intent(getApplicationContext(), Tabbed_Stats_Manager.class);
//				startActivity(intent);
//			}
//		});

//		matchList= new Connector(myIP.getIp(),"match");
//
//		text1 = findViewById(R.id.textView);
//		text3 = findViewById(R.id.textView3);

    }
}