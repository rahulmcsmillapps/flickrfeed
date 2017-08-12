package com.millapps.flickrfeed.viewmodel;

import com.millapps.flickrfeed.model.entity.PublicFeedItem;

public class PublicFeedListItemViewModel {
    private PublicFeedItem publicFeedItem;

    public PublicFeedListItemViewModel(PublicFeedItem publicFeedItem) {
        this.publicFeedItem = publicFeedItem;
    }

    public String getAuthor() {
        return publicFeedItem.getAuthor();
    }

    public String getDateTaken() {
        return publicFeedItem.getDateTaken();
    }

    public String getDatePublished() {
        return publicFeedItem.getPublished();
    }

    public String getImageUrl() {
        //request a bigger square size (150X150) as per Flickr API guide
        //https://www.flickr.com/services/api/misc.urls.html
        return publicFeedItem.getMedia().getMediaUrl().replace("_m", "_q");
    }
}