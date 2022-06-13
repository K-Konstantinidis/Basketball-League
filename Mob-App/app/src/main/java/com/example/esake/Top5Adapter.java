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

public class Top5Adapter extends RecyclerView.Adapter<Top5Adapter.Top5Holder>{

	private Context mCtx;
	private List<Top5> top5List;

	public Top5Adapter(Context mCtx, List<Top5> top5List) {
		this.mCtx = mCtx;
		this.top5List = top5List;
	}

	@NonNull
	@Override
	public Top5Adapter.Top5Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(mCtx);
		View view = inflater.inflate(R.layout.row_top5, null);
		return new Top5Adapter.Top5Holder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull Top5Adapter.Top5Holder holder, int position) {
		Top5 top5 = top5List.get(position);

		Picasso.with(mCtx.getApplicationContext()).load(top5.getLogoPath()).fit().into(holder.imageView);
		holder.textViewNameTop5.setText(top5.getName());
		holder.textViewPositionTop5.setText(top5.getPos());
		holder.textViewRatingTop5.setText(top5.getRating());
	}

	@Override
	public int getItemCount() {
		return top5List.size();
	}

	static class Top5Holder extends RecyclerView.ViewHolder{

		ImageView imageView;
		TextView textViewNameTop5,textViewPositionTop5, textViewRatingTop5;
		public Top5Holder(View itemView) {
			super(itemView);

			imageView = (ImageView) itemView.findViewById(R.id.imgTop5);
			textViewNameTop5 = itemView.findViewById(R.id.nameTop5);
			textViewPositionTop5 = itemView.findViewById(R.id.positionTop5);
			textViewRatingTop5 = itemView.findViewById(R.id.ratingTop5);
			}
	}
}
