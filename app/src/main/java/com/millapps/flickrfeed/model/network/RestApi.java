package com.millapps.flickrfeed.model.network;

import com.millapps.flickrfeed.model.entity.PublicFeed;

import io.reactivex.Observable;

public interface RestApi {
    Observable<PublicFeed> fetchPublicFeed(boolean forceFetch);
}