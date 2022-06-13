package com.example.esake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerHolder>{
	private Context mCtx;
	private List<Player> playerList;

	public PlayerAdapter(Context mCtx, List<PlayerStats> playerStatsList) {
		this.mCtx = mCtx;
		this.playerList = playerList;
	}

	@NonNull
	@Override
	public PlayerAdapter.PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.row_team_management_startingteam_stats_manager, null);
		return new PlayerAdapter.PlayerHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull PlayerAdapter.PlayerHolder holder, int position) {
		Player player = playerList.get(position);

//		holder.spinnerHome;
//		holder.spinnerAway;
	}

	@Override
	public int getItemCount() {	return playerList.size();}

	public class PlayerHolder extends RecyclerView.ViewHolder {

		Spinner spinnerHome, spinnerAway;

		public PlayerHolder(View itemView) {
			super(itemView);

			spinnerHome = itemView.findViewById(R.id.spinnerHome);
			spinnerAway = itemView.findViewById(R.id.spinnerAway);
		}
	}
}
