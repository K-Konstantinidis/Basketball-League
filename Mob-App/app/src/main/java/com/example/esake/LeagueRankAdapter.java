package com.example.esake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LeagueRankAdapter extends RecyclerView.Adapter<LeagueRankAdapter.LeagueRankHolder>{

	private Context mCtx;
	private List<LeagueRank> leagueRankList;

	public LeagueRankAdapter(Context mCtx, List<LeagueRank> leagueRankList) {
		this.mCtx = mCtx;
		this.leagueRankList = leagueRankList;
	}

	@NonNull
	@Override
	public LeagueRankHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.league_table_row, null);
		return new LeagueRankHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull LeagueRankHolder holder, int position) {
		LeagueRank leagueRank = leagueRankList.get(position);

		holder.textViewTeamName.setText(leagueRank.getName());
		holder.textViewTeamGames.setText(leagueRank.getMatchesPlayed());
		holder.textViewTeamPoints.setText(leagueRank.getPoints());
		holder.textViewTeamWins.setText(leagueRank.getWins());
		holder.textViewTeamLosses.setText(leagueRank.getLosses());
		holder.imageView.setImageResource(R.drawable.five);

	}

	@Override
	public int getItemCount() {
		return leagueRankList.size();
	}

	static class LeagueRankHolder extends RecyclerView.ViewHolder{

		ImageView imageView;
		TextView textViewTeamName,textViewTeamGames, textViewTeamPoints, textViewTeamWins, textViewTeamLosses;
		public LeagueRankHolder(View itemView) {
			super(itemView);

			imageView = itemView.findViewById(R.id.league_table_team_logo);
			textViewTeamName = itemView.findViewById(R.id.league_table_teamName);
			textViewTeamGames = itemView.findViewById(R.id.league_table_games_value);
			textViewTeamPoints = itemView.findViewById(R.id.league_table_points_value);
			textViewTeamWins = itemView.findViewById(R.id.league_table_wins_value);
			textViewTeamLosses = itemView.findViewById(R.id.league_table_losses_value);
		}
	}
}
