package com.millapps.flickrfeed.model;

import com.millapps.flickrfeed.model.entity.PublicFeed;
import com.millapps.flickrfeed.model.network.RestApi;

import io.reactivex.Observable;
import timber.log.Timber;

public class PublicFeedDataModel {
    private static final String TAG = PublicFeedDataModel.class.getName();
    private RestApi restApi;
    private PublicFeed publicFeed;

    public PublicFeedDataModel(RestApi restApi) {
        this.restApi = restApi;
        publicFeed = new PublicFeed();
    }

    public Observable<PublicFeed> publicFeed() {
        Timber.d(TAG, "start publicFeed");
        return restApi.fetchPublicFeed(false)
                .concatWith(restApi.fetchPublicFeed(true))
                .onErrorReturn(throwable -> {
                    Timber.d(throwable);
                    return new PublicFeed();
                }).map(publicFeed1 -> {
                    Timber.d(TAG, "Set most recent feed " + publicFeed1.getModified());
                    publicFeed = publicFeed1;
                    return publicFeed;
                });
    }

}

