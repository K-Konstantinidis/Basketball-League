package com.example.esake.Adapters;

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
import com.example.esake.TabbedView.Tabbed_User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeUserAdapter extends RecyclerView.Adapter<HomeUserAdapter.HomeUserHolder>{

	private Context mCtx;
	private List<GameWeek> homeUserGamesList;
	private String round_id;

	public HomeUserAdapter(Context mCtx, List<GameWeek> homeUserGamesList, String round_id) {
		this.mCtx = mCtx;
		this.homeUserGamesList = homeUserGamesList;
		this.round_id = round_id;
	}


	@NonNull
	@Override
	public HomeUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		@SuppressLint("InflateParams") View view = inflater.inflate(R.layout.row_home_user, null);
		return new HomeUserAdapter.HomeUserHolder(view);
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void onBindViewHolder(@NonNull HomeUserAdapter.HomeUserHolder holder, int position) {
		GameWeek gameWeek = homeUserGamesList.get(position);
		int game_status;

		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getHomeLogo()).fit().into(holder.imageViewHome);
		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getAwayLogo()).fit().into(holder.imageViewAway);

		game_status = gameWeek.getGameStatus();
		setScoreText(holder.textViewScore1, gameWeek, game_status, true);
		setScoreText(holder.textViewScore2, gameWeek, game_status, false);

		switch(game_status){
			case 0:
				holder.button.setText("Game Summary");
				holder.button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(mCtx, Tabbed_User.class);
						intent.putExtra("round", round_id);
						intent.putExtra("status", game_status);

						String game;
						game = gameWeek.getGameId();
						intent.putExtra("game", game);

						mCtx.startActivity(intent);
					}
				});
				break;
			case 1:
				holder.button.setText("Watch Live");
				holder.button.setBackgroundColor(Color.RED);
				holder.button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(mCtx, Tabbed_User.class);
						intent.putExtra("round", round_id);
						intent.putExtra("status", game_status);

						String game;
						game = gameWeek.getGameId();
						intent.putExtra("game", game);

						mCtx.startActivity(intent);

					}
				});
				break;
			case 2:
				holder.button.setText("Scheduled");
				holder.button.setEnabled(false);
				holder.button.setClickable(false);
				holder.button.setBackgroundColor(Color.GRAY);
				break;
		}
	}

	private void setScoreText(TextView textViewScore, GameWeek gameWeek, int gameStatus, boolean isHomeTeam) {
		textViewScore.setText(getStringScore(gameWeek,isHomeTeam, gameStatus));
		textViewScore.setTypeface(Typeface.DEFAULT_BOLD);
		if (gameStatus==1)
			textViewScore.setTextColor(Color.RED);
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
	public int getItemCount() {return homeUserGamesList.size();	}

	public class HomeUserHolder extends RecyclerView.ViewHolder{

		ImageView imageViewHome, imageViewAway;
		TextView textViewScore1, textViewScore2;
		Button button;
		public HomeUserHolder(View itemView) {
			super(itemView);

			imageViewHome = itemView.findViewById(R.id.gameweek_team1_logo);
			imageViewAway = itemView.findViewById(R.id.gameweek_team2_logo);
			textViewScore1 = itemView.findViewById(R.id.gameweek_preview_team1_score);
			textViewScore2 = itemView.findViewById(R.id.gameweek_preview_team2_score);
			button = itemView.findViewById(R.id.gameweek_preview_gameButton);
		}
	}
}
