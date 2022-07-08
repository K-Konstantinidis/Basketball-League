package com.example.esake.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esake.R;
import com.example.esake.TeamStats;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.TeamStatsHolder>{
	private Context mCtx;
	private List<TeamStats> teamStatsList;

	public TeamStatsAdapter(Context mCtx, List<TeamStats> teamStatsList) {
		this.mCtx = mCtx;
		this.teamStatsList = teamStatsList;
	}

	@NonNull
	@Override
	public TeamStatsAdapter.TeamStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		@SuppressLint("InflateParams") View view = inflater.inflate(R.layout.row_team_stats, null);
		return new TeamStatsAdapter.TeamStatsHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull TeamStatsAdapter.TeamStatsHolder holder, int position) {
		TeamStats teamStats = teamStatsList.get(position);

		Picasso.with(mCtx.getApplicationContext()).load(teamStats.getLogo()).fit().into(holder.imageView);
		holder.textViewTeamName.setText(teamStats.getName());
		holder.textViewTeamPoints.setText(teamStats.getTotal_points());
		holder.textViewTeamFg.setText(teamStats.getPerc_2_in());
		holder.textViewTeam3Fg.setText(teamStats.getPerc_3_in());
		holder.textViewTeamPercentFg.setText(teamStats.getPerc_freethrows_in());
		holder.textViewTeamReb.setText(teamStats.getTotal_rebounds());
		holder.textViewTeamAst.setText(teamStats.getTotal_assists());
		holder.textViewTeamBlock.setText(teamStats.getTotal_steals());
		holder.textViewTeamStl.setText(teamStats.getTotal_blocks());
		holder.textViewTeamFoul.setText(teamStats.getTotal_fouls());
		holder.textViewTeamTO.setText(teamStats.getTotal_turnovers());
	}

	@Override
	public int getItemCount() {	return teamStatsList.size();}

	public class TeamStatsHolder extends RecyclerView.ViewHolder {

		ImageView imageView;
		TextView textViewTeamName, textViewTeamPoints,
			textViewTeamFg, textViewTeam3Fg, textViewTeamPercentFg,
			textViewTeamReb, textViewTeamAst, textViewTeamStl,
			textViewTeamBlock, textViewTeamFoul, textViewTeamTO;

		public TeamStatsHolder(View itemView) {
			super(itemView);

			imageView = itemView.findViewById(R.id.team_stats_team_logo);
			textViewTeamName = itemView.findViewById(R.id.team_stats_teamName);
			textViewTeamPoints = itemView.findViewById(R.id.team_stats_pts_value);
			textViewTeamFg = itemView.findViewById(R.id.team_stats_fg_value);
			textViewTeam3Fg = itemView.findViewById(R.id.team_stats_3fg_value);
			textViewTeamPercentFg = itemView.findViewById(R.id.team_stats_percentfg_value);
			textViewTeamReb = itemView.findViewById(R.id.team_stats_reb_value);
			textViewTeamAst = itemView.findViewById(R.id.team_stats_ast_value);
			textViewTeamStl = itemView.findViewById(R.id.team_stats_stl_value);
			textViewTeamBlock = itemView.findViewById(R.id.team_stats_block_value);
			textViewTeamFoul = itemView.findViewById(R.id.team_stats_foul_value);
			textViewTeamTO = itemView.findViewById(R.id.team_stats_turnover_value);
		}
	}
}
