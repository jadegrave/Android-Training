package com.metova.javadevelopers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jdegr on 8/1/2017.
 */


// Gets the response of the Service
public class ItemResponse {
    @SerializedName("Items")
    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems (List<Item> items){
        this.items = items;
    }

}
