package com.millapps.flickrfeed.model.entity;

import com.google.gson.annotations.SerializedName;

public class PublicFeedItem {

    private String author;
    private String link;
    private String description;
    private Media media;
    private String published;
    private String title;
    private String tags;

    @SerializedName("author_id")
    private String authorId;

    @SerializedName("date_taken")
    private String dateTaken;

    public PublicFeedItem() {
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

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

    public void setMedia(Media media) {
        this.media = media;
    }

    public Media getMedia() {
        return media;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getPublished() {
        return published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return
                "PublicFeedItem{" +
                        "author = '" + author + '\'' +
                        ",link = '" + link + '\'' +
                        ",description = '" + description + '\'' +
                        ",media = '" + media + '\'' +
                        ",published = '" + published + '\'' +
                        ",title = '" + title + '\'' +
                        ",author_id = '" + authorId + '\'' +
                        ",date_taken = '" + dateTaken + '\'' +
                        ",tags = '" + tags + '\'' +
                        "}";
    }
}