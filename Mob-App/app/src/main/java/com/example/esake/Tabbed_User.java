package com.example.esake;

import android.content.Intent;
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
    private ImageView homeTeamImage, awayTeamImage;
    private TextView homeTeamScore, awayTeamScore;
    private TextView timer;
	private Connector c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabbedUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        homeTeamImage = (ImageView) findViewById(R.id.home_team_user);
		awayTeamImage = (ImageView) findViewById(R.id.away_team_user);
		homeTeamScore = findViewById(R.id.home_team_score_user);
		awayTeamScore = findViewById(R.id.away_team_score_user);
		timer = findViewById(R.id.timeline_user);

		c = new Connector(myIP.getIp(),"finished-match-team-scores");

		homeTeamScore.setText(String.valueOf(c.getFinishedGame().getScore1()));
		awayTeamScore.setText(String.valueOf(c.getFinishedGame().getScore2()));
		timer.setText("69'");

		Picasso.with(getApplicationContext()).load(c.getFinishedGame().getHomeTeamLogo()).fit().into(homeTeamImage);
		Picasso.with(getApplicationContext()).load(c.getFinishedGame().getAwayTeamLogo()).fit().into(awayTeamImage);

	}
}