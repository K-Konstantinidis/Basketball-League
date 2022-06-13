package com.example.esake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerStatsAdapter extends RecyclerView.Adapter<PlayerStatsAdapter.PlayerStatsHolder>{
	private Context mCtx;
	private List<PlayerStats> playerStatsList;

	public PlayerStatsAdapter(Context mCtx, List<PlayerStats> playerStatsList) {
		this.mCtx = mCtx;
		this.playerStatsList = playerStatsList;
	}

	@NonNull
	@Override
	public PlayerStatsAdapter.PlayerStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.row_player_stats, null);
		return new PlayerStatsAdapter.PlayerStatsHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PlayerStatsAdapter.PlayerStatsHolder holder, int position) {
		PlayerStats playerStats = playerStatsList.get(position);

		Picasso.with(mCtx.getApplicationContext()).load(playerStats.getLogo()).fit().into(holder.imageView);
		holder.textViewPlayerName.setText(playerStats.getSurname());
		holder.TextViewPlayerRating.setText(playerStats.getPRating());
		holder.textViewPlayerPoints.setText(playerStats.getTotal_points());
		holder.textViewPlayerFg.setText(playerStats.getPerc_2_in());
		holder.textViewPlayer3Fg.setText(playerStats.getPerc_3_in());
		holder.textViewPlayerPercentFg.setText(playerStats.getPerc_freethrows_in());
		holder.textViewPlayerReb.setText(playerStats.getTotal_rebounds());
		holder.textViewPlayerAst.setText(playerStats.getTotal_assists());
		holder.textViewPlayerStl.setText(playerStats.getTotal_steals());
		holder.textViewPlayerBlock.setText(playerStats.getTotal_blocks());
		holder.textViewPlayerFoul.setText(playerStats.getTotal_fouls());
		holder.textViewPlayerTO.setText(playerStats.getTotal_turnovers());
	}

	@Override
	public int getItemCount() {	return playerStatsList.size();}

	public class PlayerStatsHolder extends RecyclerView.ViewHolder {

		ImageView imageView;
		TextView textViewPlayerName, TextViewPlayerRating, textViewPlayerPoints,
			textViewPlayerFg, textViewPlayer3Fg, textViewPlayerPercentFg,
			textViewPlayerReb, textViewPlayerAst, textViewPlayerStl,
			textViewPlayerBlock, textViewPlayerFoul, textViewPlayerTO;

		public PlayerStatsHolder(View itemView) {
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.player_stats_team_logo);
			textViewPlayerName = itemView.findViewById(R.id.player_stats_playerName);
			TextViewPlayerRating = itemView.findViewById(R.id.player_stats_rating_value);
			textViewPlayerPoints = itemView.findViewById(R.id.player_stats_pts_value);
			textViewPlayerFg = itemView.findViewById(R.id.player_stats_fg_value);
			textViewPlayer3Fg = itemView.findViewById(R.id.player_stats_3fg_value);
			textViewPlayerPercentFg = itemView.findViewById(R.id.player_stats_percentfg_value);
			textViewPlayerReb = itemView.findViewById(R.id.player_stats_reb_value);
			textViewPlayerAst = itemView.findViewById(R.id.player_stats_ast_value);
			textViewPlayerStl = itemView.findViewById(R.id.player_stats_stl_value);
			textViewPlayerBlock = itemView.findViewById(R.id.player_stats_block_value);
			textViewPlayerFoul = itemView.findViewById(R.id.player_stats_foul_value);
			textViewPlayerTO = itemView.findViewById(R.id.player_stats_turnover_value);
		}
	}
}
