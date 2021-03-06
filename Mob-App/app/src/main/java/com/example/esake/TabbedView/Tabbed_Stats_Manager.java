package com.example.esake.TabbedView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.esake.R;
import com.example.esake.StatsManagerFragments.FragmentSmGameManage;
import com.example.esake.StatsManagerFragments.FragmentSmGameOverview;
import com.example.esake.StatsManagerFragments.FragmentSmTeamManage;
import com.example.esake.databinding.ActivityTabbedStatsManagerBinding;
import com.example.esake.TabbedView.TabbedViewStatsManager.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class Tabbed_Stats_Manager extends AppCompatActivity {

    private ActivityTabbedStatsManagerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabbedStatsManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = binding.viewPagerSm;

		Bundle b;
		b = getIntent().getExtras();
		String homeLogo = b.getString("homeLogo");
		String awayLogo = b.getString("awayLogo");
		int game_status = b.getInt("gameStatus");

		String homeTeamId = getTeamId(homeLogo);
		String awayTeamId = getTeamId(awayLogo);

		FragmentSmGameOverview viewSM = FragmentSmGameOverview.newInstance(homeTeamId,awayTeamId);
		FragmentSmGameManage playerS = FragmentSmGameManage.newInstance(homeLogo,awayLogo);
		FragmentSmTeamManage teamS = FragmentSmTeamManage.newInstance(homeTeamId,awayTeamId);

		/*Text for tabs*/
		sectionsPagerAdapter.AddFragment(viewSM,"Game Overview");
		sectionsPagerAdapter.AddFragment(playerS, "Manage Game");
		sectionsPagerAdapter.AddFragment(teamS, "Manage Team");

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabsSm;
        tabs.setupWithViewPager(viewPager);

		ImageView homeTeamImage = findViewById(R.id.home_team_stats_manager);
		ImageView awayTeamImage = findViewById(R.id.away_team_stats_manager);
		TextView homeTeamScore = findViewById(R.id.home_team_score_stats_manager);
		TextView awayTeamScore = findViewById(R.id.away_team_score_stats_manager);
		TextView timer = findViewById(R.id.timeline_stats_manager);

		homeTeamScore.setText("???");
		awayTeamScore.setText("???");

		timer.setText("???");

		Picasso.with(getApplicationContext()).load(homeLogo).fit().into(homeTeamImage);
		Picasso.with(getApplicationContext()).load(awayLogo).fit().into(awayTeamImage);

	}

	private String getTeamId(String homeLogo) {
		String sub = homeLogo.substring(homeLogo.length()-6,homeLogo.length()-4);
		return String.valueOf(Integer.parseInt(sub));
	}

}