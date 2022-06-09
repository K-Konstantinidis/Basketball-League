package com.example.esake.StatsManagerHome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esake.Connector;
import com.example.esake.R;
import com.example.esake.Tabbed_Stats_Manager;
import com.example.esake.myIP;


public class HomeActivity extends AppCompatActivity {

	private Connector matchList;
	private TextView text1, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_stats_manager);

		//Get the button to start a game
		Button b = findViewById(R.id.watchGame);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Go to the tabbed view for the game
				Intent intent = new Intent(getApplicationContext(), Tabbed_Stats_Manager.class);
				startActivity(intent);
			}
		});

		matchList= new Connector(myIP.getIp(),"match");

		text1 = findViewById(R.id.textView);
		text3 = findViewById(R.id.textView3);

		text1.setText(matchList.getScoreList(0, true));
		text3.setText(matchList.getScoreList(0,false));
    }
}