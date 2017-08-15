
package com.metova.musixmatch.model;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistList {


    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    @VisibleForTesting
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
