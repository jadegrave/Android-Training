package com.metova.musixmatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jodi on 8/2/17.
 */

public class ItemResponse {
    @SerializedName("message")
    @Expose
    private List<Item> me

}
