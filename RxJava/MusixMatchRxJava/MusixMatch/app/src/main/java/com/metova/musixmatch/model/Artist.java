
package com.metova.musixmatch.model;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("artist_name")
    @Expose
    private String artistName;

    @SerializedName("artist_rating")
    @Expose
    private Integer artistRating;

    @SerializedName("artist_share_url")
    @Expose
    private String artistShareUrl;

    public String getArtistName() {
        return artistName;
    }

    public Integer getArtistRating() {
        return artistRating;
    }

    public String getArtistShareUrl() {
        return artistShareUrl;
    }

//    @VisibleForTesting
//    public Artist(String artistName, Integer artistRating, String artistShareUrl) {
//        this.artistName = artistName;
//        this.artistRating = artistRating;
//        this.artistShareUrl = artistShareUrl;
//    }

    @VisibleForTesting
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @VisibleForTesting
    public void setArtistRating(Integer artistRating) {
        this.artistRating = artistRating;
    }

    @VisibleForTesting
    public void setArtistShareUrl(String artistShareUrl) {
        this.artistShareUrl = artistShareUrl;
    }
}
