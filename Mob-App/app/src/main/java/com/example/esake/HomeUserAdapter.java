package com.example.esake;

import android.content.Context;
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

public class HomeUserAdapter extends RecyclerView.Adapter<HomeUserAdapter.HomeUserHolder>{

	private Context mCtx;
	private List<GameWeek> homeUserGamesList;

	public HomeUserAdapter(Context mCtx, List<GameWeek> homeUserGamesList) {
		this.mCtx = mCtx;
		this.homeUserGamesList = homeUserGamesList;
	}


	@NonNull
	@Override
	public HomeUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.row_home_user, null);
		return new HomeUserAdapter.HomeUserHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull HomeUserAdapter.HomeUserHolder holder, int position) {
		GameWeek gameWeek = homeUserGamesList.get(position);

		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getHomeLogo()).fit().into(holder.imageViewHome);
		Picasso.with(mCtx.getApplicationContext()).load(gameWeek.getAwayLogo()).fit().into(holder.imageViewAway);
		holder.textViewScore1.setText(gameWeek.getHomeScore());
		holder.textViewScore2.setText(gameWeek.getAwayScore());
		holder.button.setText("Changed Text"); //Ayto tha ginei gameWeek.getGameStatus()
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
