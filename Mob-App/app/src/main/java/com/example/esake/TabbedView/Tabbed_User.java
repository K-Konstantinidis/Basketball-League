package com.example.esake.TabbedView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.esake.Connector;
import com.example.esake.R;
import com.example.esake.UserFragments.FragmentUserGameOverview;
import com.example.esake.UserFragments.FragmentUserPlayerStatsFinished_Live;
import com.example.esake.UserFragments.FragmentUserTeamStatsFinished_Live;
import com.example.esake.databinding.ActivityTabbedUserBinding;
import com.example.esake.MyIP;
import com.example.esake.TabbedView.TabbedViewUser.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class Tabbed_User extends AppCompatActivity {

    private ActivityTabbedUserBinding binding;
    private Connector tabUser;
    private ImageView homeTeamImage, awayTeamImage;
    private TextView homeTeamScore, awayTeamScore;
    private TextView timer;


    @SuppressLint("SetTextI18n")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabbedUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;

		Bundle b;
		b = getIntent().getExtras();
		String round = b.getString("round");
		int gameStatus = b.getInt("status");
		String gameID = b.getString("game");

		String url = "getOngoingMatchTime.php?cid=1&round="+round+"&gid="+gameID;

		FragmentUserGameOverview viewUser = FragmentUserGameOverview.newInstance(round,
			String.valueOf(gameStatus),gameID);
		FragmentUserPlayerStatsFinished_Live playerS = FragmentUserPlayerStatsFinished_Live.newInstance(round,
			String.valueOf(gameStatus),gameID);
		FragmentUserTeamStatsFinished_Live teamS = FragmentUserTeamStatsFinished_Live.newInstance(round,
			String.valueOf(gameStatus),gameID);

		sectionsPagerAdapter.AddFragment(viewUser,"Game");
		sectionsPagerAdapter.AddFragment(teamS, "Team Stats");
		sectionsPagerAdapter.AddFragment(playerS, "Player Stats");

		viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

		homeTeamScore = findViewById(R.id.home_team_score_user);
		homeTeamImage = findViewById(R.id.home_team_user);
		awayTeamImage = findViewById(R.id.away_team_user);
		awayTeamScore = findViewById(R.id.away_team_score_user);
		timer = findViewById(R.id.timeline_user);

		try {
			tabUser = new Connector(MyIP.getIp(), "getMatchScores.php?lang=gr&cid=1&rid="+round+"&gid="+gameID);
			homeTeamScore.setText(String.valueOf(tabUser.TabbedUser().getScore1()));
			awayTeamScore.setText(String.valueOf(tabUser.TabbedUser().getScore2()));
			Picasso.with(getApplicationContext()).load(tabUser.TabbedUser().getHomeTeamLogo()).fit().into(homeTeamImage);
			Picasso.with(getApplicationContext()).load(tabUser.TabbedUser().getAwayTeamLogo()).fit().into(awayTeamImage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//timer.setText(timeTracker.getCurrentMinute(gameStatus));
		//timer.setText(tabUser.getCurrentMinute(gameStatus));

		switch (gameStatus) {
			case 2:
				timer.setText("—");
				break;
			case 1:
				timer.setText("35'");
				break;
			case 0:
				timer.setText("40'");
				break;
		}
    }
}