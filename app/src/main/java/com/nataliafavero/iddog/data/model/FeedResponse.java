package com.nataliafavero.iddog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliafavero on 07/03/18.
 */

public class FeedResponse {

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("list")
    @Expose
    private String[] images;

    public String getCategory() {
        return category;
    }

    public String[] getImages() {
        return images;
    }
}
