package com.millapps.flickrfeed.model;

import com.millapps.flickrfeed.model.entity.PublicFeed;
import com.millapps.flickrfeed.model.network.RestApi;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Acts as the data source for the Flickr Feed serving data from a local cache (if some exists) before  pulling
 * down fresh data from the flickr feed server. This could well be more efficiently implemented using a
 * reactive db like Realm (in which case this could also be modelled on the repository pattern)
 * but serves our current purpose well enough.
 */
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
                .onErrorResumeNext(throwable -> {
                    Timber.d(throwable);
                    return Observable.just(new PublicFeed());
                })
                .concatWith(restApi.fetchPublicFeed(true))
                .map(publicFeed1 -> {
                    Timber.d(TAG, "Set most recent feed " + publicFeed1.getModified());
                    publicFeed = publicFeed1;
                    return publicFeed;
                });
    }

}

