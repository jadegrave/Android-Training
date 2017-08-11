
package com.metova.musixmatch.model;


import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistsResults {

    @SerializedName("message")
    @Expose
    private Message message;

    public Message getMessage() {
        return message;
    }

    @VisibleForTesting
    public void setMessage(Message message) {
        this.message = message;
    }
}
