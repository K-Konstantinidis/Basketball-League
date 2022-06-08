package com.example.esake.StatsManagerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.esake.Championship;
import com.example.esake.FragmentTop5;
import com.example.esake.FragmentUserHome;
import com.example.esake.GameWeek;
import com.example.esake.R;
import com.example.esake.Tabbed_Stats_Manager;
import com.example.esake.myIP;


public class HomeActivity extends AppCompatActivity {

	private Championship matches;
	private GameWeek matchList;
	private TextView text1, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_stats_manager);

//        //Get a home frag for the stats manager
//        HomeFragment homefrag = new HomeFragment();
//
////      FragmentTop5 top5 = new FragmentTop5();
//
//        FragmentManager manager = getSupportFragmentManager();
//        transaction = manager.beginTransaction();
//		//for top5 fragment
//        // transaction.replace(R.id.homefrag, top5, null);
//        transaction.replace(R.id.homefrag, homefrag,null); //instead of replace you can also have add
//        transaction.commit();

		//Get the button to start a game
		Button b = (Button) findViewById(R.id.watchGame);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Go to the tabbed view for the game
				Intent intent = new Intent(getApplicationContext(), Tabbed_Stats_Manager.class);
				startActivity(intent);
			}
		});

		text1 = findViewById(R.id.textView);
		text3 = findViewById(R.id.textView3);

		matchList = new GameWeek(myIP.getIp());

		text1.setText(matchList.getMatchList(2,true));
		text3.setText(matchList.getMatchList(2,false));
    }
}