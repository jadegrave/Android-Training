package com.metova.musixmatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metova.musixmatch.model.Artist;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jodi on 8/1/17.
 */


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private List<Artist> mArtists;
    private RxEventBus mBus = RxEventBus.getInstance();

    public ArtistAdapter(List<Artist> artistArrayList) {
        this.mArtists = artistArrayList;
    }

    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // the int i is the view type; you could override the getItemViewType();
        // if you don't override it with it, it just returns 0

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_artist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistAdapter.ViewHolder viewHolder, int i) {

        Artist selectedArtist = mArtists.get(i);
        viewHolder.name.setText(selectedArtist.getArtistName());
        viewHolder.link.setText(selectedArtist.getArtistShareUrl());
        viewHolder.rating.setText(String.valueOf(selectedArtist.getArtistRating()));
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public RxEventBus bus() {
        return mBus;
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
                    Events cardOnClickEvent = new Events(pos);
                    mBus.post(cardOnClickEvent);
                }
            });
        }
    }
}

