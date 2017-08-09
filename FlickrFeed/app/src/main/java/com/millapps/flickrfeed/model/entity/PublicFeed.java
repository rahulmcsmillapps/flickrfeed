package com.millapps.flickrfeed.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublicFeed {

    private String modified;
    private String link;
    private String description;
    private String generator;
    private String title;

    @SerializedName("items")
    private List<PublicFeedItem> items;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModified() {
        return modified;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getGenerator() {
        return generator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<PublicFeedItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return
                "PublicFeed{" +
                        "link = '" + link + '\'' +
                        ",description = '" + description + '\'' +
                        ",modified = '" + modified + '\'' +
                        ",generator = '" + generator + '\'' +
                        ",title = '" + title + '\'' +
                        ",items = '" + items + '\'' +
                        "}";
    }
}