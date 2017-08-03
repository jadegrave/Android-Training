package com.metova.musixmatch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.metova.musixmatch.controller.DetailActivity;
import com.metova.musixmatch.model.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jodi on 8/3/17.
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
        viewHolder.link.setText(artists.get(i).getArtistTwitterUrl());

        Picasso.with(context)
                .load(artists.get(i).getArtistRating())
                .placeholder(R.drawable.load)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, link;
        private ImageView imageView;


        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            link = (TextView) view.findViewById(R.id.link);
            imageView = (ImageView) view.findViewById(R.id.cover);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Artist clickedDataItem = artists.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("artist_name", artists.get(pos).getArtistName());
                        intent.putExtra("artist_twitter_url", artists.get(pos).getArtistTwitterUrl());
                        //intent.putExtra("avatar_url", artists.get(pos).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getArtistName(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}

