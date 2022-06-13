package com.example.esake.StatsManagerHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeSmAdapter extends RecyclerView.Adapter<HomeSmAdapter.HomeSmHolder> {

	private Context mCtx;
	private List<GameWeek> homeSmList;

	public HomeSmAdapter(Context mCtx, List<GameWeek> homeSmList) {
		this.mCtx = mCtx;
		this.homeSmList = homeSmList;
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
		holder.textViewScore1.setText(gameWeek.getHomeScore());
		holder.textViewScore2.setText(gameWeek.getAwayScore());
		game_status = gameWeek.getGameStatus();
		switch(game_status){
			case -1:
				holder.button.setText("Live Game");
				holder.button.setEnabled(false);
				holder.button.setClickable(false);
				holder.button.setBackgroundColor(Color.RED);
				break;
			case 0:
				holder.button.setText("Start Game");
				break;
			case 1:
				holder.button.setText("Scheduled");
				holder.button.setEnabled(false);
				holder.button.setClickable(false);
				holder.button.setBackgroundColor(Color.GRAY);
				break;
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
