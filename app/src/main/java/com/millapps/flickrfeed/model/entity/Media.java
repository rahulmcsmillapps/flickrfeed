package com.millapps.flickrfeed.model.entity;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("m")
    private String mediaUrl;

    public Media(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    @Override
    public String toString() {
        return
                "Media{" +
                        "m = '" + mediaUrl + '\'' +
                        "}";
    }
}