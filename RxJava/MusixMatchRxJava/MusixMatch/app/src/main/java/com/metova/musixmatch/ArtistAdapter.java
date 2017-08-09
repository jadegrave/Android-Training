package com.metova.musixmatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.metova.musixmatch.controller.DetailActivity;
import com.metova.musixmatch.model.Artist;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jodi on 8/1/17.
 */


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private List<Artist> artists;
    private Context context;

    public ArtistAdapter(Context applicationContext, List<Artist> artistArrayList) {
        this.context = applicationContext;
        this.artists = artistArrayList;
    }

    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_artist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(artists.get(i).getArtistName());
        viewHolder.link.setText(artists.get(i).getArtistShareUrl());
        viewHolder.rating.setText(String.valueOf(artists.get(i).getArtistRating()));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name) TextView name;
        @BindView(R.id.link) TextView link;
        @BindView(R.id.rating) TextView rating;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    Artist clickedDataItem = artists.get(pos);


                    //move to Main Activity
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("artist_name", artists.get(pos).getArtistName());
                    intent.putExtra("artist_rating", artists.get(pos).getArtistRating());
                    intent.putExtra("artist_share_url", artists.get(pos).getArtistShareUrl());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getArtistName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

