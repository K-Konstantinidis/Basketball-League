package com.example.esake;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchesRecViewAdaptor extends RecyclerView.Adapter<MatchesRecViewAdaptor.ViewHolder>{

	private ArrayList<Championship> games = new ArrayList<>();

	public MatchesRecViewAdaptor() {
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_fixture,
			parent, false);
		//if you don't know what the parent will be
		//View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_fixture, null);

		ViewHolder holder = new ViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		//holder.score1.setText(games.get(position).getHome_teamscore());
		//holder.score2.setText(games.get(position).getAway_teamscore());
	}

	@Override
	public int getItemCount() {
		return games.size();
	}

	public void setGames(ArrayList<Championship> games) {
		this.games = games;
		notifyDataSetChanged();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{

		private ImageView image1;
		private TextView score1;

		private TextView between;

		private ImageView image2;
		private TextView score2;

		private Button watch;


		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			image1 = itemView.findViewById(R.id.gameweek_team1_logo);
			score1 = itemView.findViewById(R.id.gameweek_preview_team1_score);

			between = itemView.findViewById(R.id.gameweekDashBetweenScores);

			image2 = itemView.findViewById(R.id.gameweek_team2_logo);
			score2 = itemView.findViewById(R.id.gameweek_preview_team2_score);

			watch = itemView.findViewById(R.id.gameweek_preview_gameButton);
		}
	}
}
