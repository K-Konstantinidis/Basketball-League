package com.example.esake.StatsManagerFragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.Adapters.HomeSmAdapter;
import com.example.esake.DatabasePHP.Connector;
import com.example.esake.GameWeek;
import com.example.esake.R;
import com.example.esake.DatabasePHP.MyIP;

import java.util.List;

public class HomeSmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_stats_manager);

		//Find the recyclerView
		RecyclerView recyclerView = findViewById(R.id.recViewHomeSM);
		//Create an adapter
		HomeSmAdapter adapter;
		List<GameWeek> homeSmList;

		//Set a vertical layout manager
		//If you are in an Activity class pass 'this'. But since
		//we are in a Fragment Class we have to pass getContext()
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		//Make a connection with the database via php
		try {
			Connector cn = new Connector(MyIP.getIp(),"getCurrentGameweek.php?cid=1");
			homeSmList = cn.weekMatches();
			adapter = new HomeSmAdapter(this, homeSmList, homeSmList.get(0).getGameweek());
			recyclerView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}