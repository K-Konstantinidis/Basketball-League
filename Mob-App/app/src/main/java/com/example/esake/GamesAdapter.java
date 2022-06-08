package com.example.esake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GameViewHolder> {

	private Context mCtx;
	private List<Game> gamesList;

	public GamesAdapter(Context mCtx, List<Game> gamesList){
		this.mCtx = mCtx;
		this.gamesList = gamesList;
	}

	@NonNull
	@Override
	public GamesAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.single_fixture, null);
		return new GameViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull GamesAdapter.GameViewHolder holder, int position) {
		Game game = gamesList.get(position);

		//loading the image
//        Glide.with(mCtx)
//                .load(product.getImage())
//                .into(holder.imageView);
//
//        holder.textViewTitle.setText(product.getTitle());
//        holder.textViewShortDesc.setText(product.getShortdesc());
//        holder.textViewRating.setText(String.valueOf(product.getRating()));
//        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
//		holder.roundId.setText(String.valueOf(game.getRoundId()));
//		holder.homeTeamId.setText(String.valueOf(game.getHomeTeamId()));
//		holder.awayTeamId.setText(String.valueOf(game.getAwayTeamId()));
	}

	@Override
	public int getItemCount() {
		return gamesList.size();
	}

	class GameViewHolder extends RecyclerView.ViewHolder {

		TextView gameId, roundId, championshipId, homeTeamId, awayTeamId;
		// ImageView imageView;

		public GameViewHolder(View itemView) {
			super(itemView);

			// gameId = itemView.findViewById(R.id.TODO);
			// championshipId = itemView.findViewById(R.id.TODO);
			roundId = itemView.findViewById(R.id.gameweekDashBetweenScores);
			homeTeamId = itemView.findViewById(R.id.gameweek_preview_team1_score);
			awayTeamId = itemView.findViewById(R.id.gameweek_preview_team2_score);
			// imageView = itemView.findViewById(R.id.imageView);
		}
	}
}

