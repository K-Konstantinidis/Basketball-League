package com.example.esake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder>{

    Context context;
    List<Game> gamesList;
    RecyclerView rvGameweek;
    final View.OnClickListener onClickListener = new MyOnClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
//        ImageView team1Logo;
//        ImageView team2Logo;
        TextView score1;
        TextView score2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            score1 = itemView.findViewById(R.id.gameweek_preview_team1_score);
            score2 = itemView.findViewById(R.id.gameweek_preview_team2_score);
//            team1Logo = itemView.findViewById(R.id.fixture_team1_logo);
//            team2Logo = itemView.findViewById(R.id.fixture_team2_logo);
        }
    }

    public GamesAdapter(Context context, List<Game> gamesList, RecyclerView rvGameweek){
        this.context = context;
        this.gamesList = gamesList;
        this.rvGameweek = rvGameweek;
    }

    @NonNull
    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_fixture, viewGroup, false);
        view.setOnClickListener(onClickListener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.ViewHolder viewHolder, int i) {
        Game game = gamesList.get(i);
        viewHolder.score1.setText(""+game.getScore1());
        viewHolder.score2.setText(""+game.getScore2());
//        viewHolder.team1Logo.setImageDrawable(R.drawable.);
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int itemPosition = rvGameweek.getChildLayoutPosition(view);
            int item = gamesList.get(itemPosition).getScore1();
        }
    }
}
