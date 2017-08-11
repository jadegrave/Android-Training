
package com.metova.musixmatch.model;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("body")
    @Expose
    private Body body;

    public Body getBody() {
        return body;
    }

    @VisibleForTesting
    public void setBody(Body body) {
        this.body = body;
    }
}
