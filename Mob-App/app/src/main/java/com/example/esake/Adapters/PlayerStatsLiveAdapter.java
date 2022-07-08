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

import com.example.esake.PlayerStats;
import com.example.esake.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerStatsLiveAdapter extends RecyclerView.Adapter<PlayerStatsLiveAdapter.PlayerStatsLiveHolder>{
	private Context mCtx;
	private List<PlayerStats> playerStatsList;

	public PlayerStatsLiveAdapter(Context mCtx, List<PlayerStats> playerStatsList) {
		this.mCtx = mCtx;
		this.playerStatsList = playerStatsList;
	}

	@NonNull
	@Override
	public PlayerStatsLiveAdapter.PlayerStatsLiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		@SuppressLint("InflateParams") View view = inflater.inflate(R.layout.row_player_stats_live_user, null);
		return new PlayerStatsLiveAdapter.PlayerStatsLiveHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PlayerStatsLiveAdapter.PlayerStatsLiveHolder holder, int position) {
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
	public int getItemCount() { return playerStatsList.size();}

	static class PlayerStatsLiveHolder extends RecyclerView.ViewHolder{

		ImageView imageView;
		TextView textViewPlayerName, TextViewPlayerRating, textViewPlayerPoints,
			textViewPlayerFg, textViewPlayer3Fg, textViewPlayerPercentFg,
			textViewPlayerReb, textViewPlayerAst, textViewPlayerStl,
			textViewPlayerBlock, textViewPlayerFoul, textViewPlayerTO;

		public PlayerStatsLiveHolder(View itemView) {
			super(itemView);

			imageView = itemView.findViewById(R.id.player_stats_live_team_logo);
			textViewPlayerName = itemView.findViewById(R.id.player_stats_live_playerName);
			TextViewPlayerRating = itemView.findViewById(R.id.player_stats_live_rating_value);
			textViewPlayerPoints = itemView.findViewById(R.id.player_stats_live_pts_value);
			textViewPlayerFg = itemView.findViewById(R.id.player_stats_live_fg_value);
			textViewPlayer3Fg = itemView.findViewById(R.id.player_stats_live_3fg_value);
			textViewPlayerPercentFg = itemView.findViewById(R.id.player_stats_live_percentfg_value);
			textViewPlayerReb = itemView.findViewById(R.id.player_stats_live_reb_value);
			textViewPlayerAst = itemView.findViewById(R.id.player_stats_live_ast_value);
			textViewPlayerStl = itemView.findViewById(R.id.player_stats_live_stl_value);
			textViewPlayerBlock = itemView.findViewById(R.id.player_stats_live_block_value);
			textViewPlayerFoul = itemView.findViewById(R.id.player_stats_live_foul_value);
			textViewPlayerTO = itemView.findViewById(R.id.player_stats_live_turnover_value);
		}
	}
}
