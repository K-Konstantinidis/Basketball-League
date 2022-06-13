package com.example.esake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

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
		View view = inflater.inflate(R.layout.row_home_user, null);
		return new HomeUserAdapter.HomeUserHolder(view);
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public void onBindViewHolder(@NonNull HomeUserAdapter.HomeUserHolder holder, int position) {
		GameWeek gameWeek = homeUserGamesList.get(position);
		int game_status;

		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getHomeLogo()).fit().into(holder.imageViewHome);
		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getAwayLogo()).fit().into(holder.imageViewAway);
		holder.textViewScore1.setText(gameWeek.getHomeScore());
		holder.textViewScore2.setText(gameWeek.getAwayScore());
		game_status = gameWeek.getGameStatus();
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

	@Override
	public int getItemCount() {return homeUserGamesList.size();	}

	public class HomeUserHolder extends RecyclerView.ViewHolder{

		ImageView imageViewHome, imageViewAway;
		TextView textViewScore1, textViewScore2;
		Button button;
		public HomeUserHolder(View itemView) {
			super(itemView);

			imageViewHome = (ImageView) itemView.findViewById(R.id.gameweek_team1_logo);
			imageViewAway = (ImageView) itemView.findViewById(R.id.gameweek_team2_logo);
			textViewScore1 = itemView.findViewById(R.id.gameweek_preview_team1_score);
			textViewScore2 = itemView.findViewById(R.id.gameweek_preview_team2_score);
			button = (Button) itemView.findViewById(R.id.gameweek_preview_gameButton);
		}
	}
}
