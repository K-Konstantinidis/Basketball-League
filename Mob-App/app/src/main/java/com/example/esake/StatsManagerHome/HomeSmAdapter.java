package com.example.esake.StatsManagerHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.GameWeek;
import com.example.esake.R;
import com.example.esake.Tabbed_Stats_Manager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSmAdapter extends RecyclerView.Adapter<HomeSmAdapter.HomeSmHolder> {

	private Context mCtx;
	private List<GameWeek> homeSmList;
	private String round_id;

	public HomeSmAdapter(Context mCtx, List<GameWeek> homeSmList, String round_id) {
		this.mCtx = mCtx;
		this.homeSmList = homeSmList;
		this.round_id = round_id;
	}

	@NonNull
	@Override
	public HomeSmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.row_home_stats_manager, null);
		return new HomeSmAdapter.HomeSmHolder(view);
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void onBindViewHolder(@NonNull HomeSmHolder holder, int position) {
		GameWeek gameWeek = homeSmList.get(position);
		int game_status;

		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getHomeLogo()).fit().into(holder.imageViewHome);
		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getAwayLogo()).fit().into(holder.imageViewAway);

		game_status = gameWeek.getGameStatus();

		setScoreText(holder.textViewScore1, gameWeek, game_status, true);
		setScoreText(holder.textViewScore2, gameWeek, game_status, false);
		switch(game_status){
			case 0:
				holder.button.setText("Game Finished");
				holder.button.setEnabled(false);
				holder.button.setClickable(false);

				holder.button.setBackgroundColor(Color.GRAY);
				break;
			case 1:
				holder.button.setText("Live Game");
				holder.button.setEnabled(false);
				holder.button.setClickable(false);
				holder.button.setBackgroundColor(Color.DKGRAY);
				break;
			case 2:
				holder.button.setText("Manage Game");
				holder.button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(mCtx, Tabbed_Stats_Manager.class);
						intent.putExtra("homeLogo", gameWeek.getHomeLogo());
						intent.putExtra("awayLogo", gameWeek.getAwayLogo());
						intent.putExtra("gameStatus", game_status);


						mCtx.startActivity(intent);
					}
				});
				break;
		}
	}

	private void setScoreText(TextView textViewScore, GameWeek gameWeek, int gameStatus, boolean isHomeTeam) {
		textViewScore.setText(getStringScore(gameWeek,isHomeTeam, gameStatus));
		textViewScore.setTypeface(Typeface.DEFAULT_BOLD);
		if (gameStatus==1)
			textViewScore.setTextColor(Color.DKGRAY);
	}

	@NonNull
	private String getStringScore(GameWeek gameWeek, boolean isHomeTeam, int gameStatus) {
		if (gameStatus==2)
			return "—";
		else {
			if (isHomeTeam)
				return gameWeek.getHomeScore();
			else
				return gameWeek.getAwayScore();
		}
	}

	@Override
	public int getItemCount() { return homeSmList.size();}

	public class HomeSmHolder extends RecyclerView.ViewHolder{

		ImageView imageViewHome, imageViewAway;
		TextView textViewScore1, textViewScore2;
		Button button;
		public HomeSmHolder(View itemView) {
			super(itemView);

			imageViewHome = (ImageView) itemView.findViewById(R.id.imageView);
			imageViewAway = (ImageView) itemView.findViewById(R.id.imageView2);
			textViewScore1 = itemView.findViewById(R.id.textView);
			textViewScore2 = itemView.findViewById(R.id.textView3);
			button = (Button) itemView.findViewById(R.id.watchGame);
		}
	}
}
