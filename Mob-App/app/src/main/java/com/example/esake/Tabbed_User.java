package com.example.esake;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.esake.databinding.ActivityTabbedUserBinding;
import com.example.esake.ui.tabbedView_User.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class Tabbed_User extends AppCompatActivity {

    private ActivityTabbedUserBinding binding;
    private Connector tabUser;

    private ImageView homeTeamImage, awayTeamImage;
    private TextView homeTeamScore, awayTeamScore;
    private TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabbedUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;

		Bundle b = new Bundle();
		b = getIntent().getExtras();
		String round = b.getString("round");
		int gameStatus = b.getInt("status");
		String gameID = b.getString("game");

		String url = "getMatchDetailedScore.php?lang=gr&cid=1&rid="+round+"&gid="+
			gameID;

		tabUser = new Connector(myIP.getIp(),"tabbed-User",url);

		FragmentMatchOverviewUser viewUser = FragmentMatchOverviewUser.newInstance(round,
			String.valueOf(gameStatus),gameID);
		FragmentPlayerStatsLiveUser playerS = FragmentPlayerStatsLiveUser.newInstance(round,
			String.valueOf(gameStatus),gameID);
		FragmentTeamStatsLiveUser teamS = FragmentTeamStatsLiveUser.newInstance(round,
			String.valueOf(gameStatus),gameID);

		sectionsPagerAdapter.AddFragment(viewUser,"Game");
		sectionsPagerAdapter.AddFragment(teamS, "Team Stats");
		sectionsPagerAdapter.AddFragment(playerS, "Player Stats");

		viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        homeTeamImage = (ImageView) findViewById(R.id.home_team_user);
		awayTeamImage = (ImageView) findViewById(R.id.away_team_user);
		homeTeamScore = findViewById(R.id.home_team_score_user);
		awayTeamScore = findViewById(R.id.away_team_score_user);
		timer = findViewById(R.id.timeline_user);

		homeTeamScore.setText(String.valueOf(tabUser.getFinishedGame().getScore1()));
		awayTeamScore.setText(String.valueOf(tabUser.getFinishedGame().getScore2()));
		timer.setText("69'");

		Picasso.with(getApplicationContext()).load(tabUser.getFinishedGame().getHomeTeamLogo()).fit().into(homeTeamImage);
		Picasso.with(getApplicationContext()).load(tabUser.getFinishedGame().getAwayTeamLogo()).fit().into(awayTeamImage);
	}
}