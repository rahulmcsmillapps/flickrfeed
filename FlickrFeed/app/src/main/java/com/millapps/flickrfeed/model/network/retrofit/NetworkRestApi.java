package com.millapps.flickrfeed.model.network.retrofit;

import com.millapps.flickrfeed.BuildConfig;
import com.millapps.flickrfeed.model.entity.PublicFeed;
import com.millapps.flickrfeed.model.network.RestApi;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import timber.log.Timber;

public class NetworkRestApi implements RestApi {
    private static final String TAG = NetworkRestApi.class.getName();
    private static final String NO_CACHE = "no-cache";
    private static final String FORCE_CACHE = "public, max-stale=241920";

    private PublicFeedApiServiceImpl flickrApiService;

    @Inject
    public NetworkRestApi(Retrofit retrofit) {
        flickrApiService = retrofit.create(PublicFeedApiServiceImpl.class);
    }

    @Override
    public Observable<PublicFeed> fetchPublicFeed(boolean forceFetch) {
        String cacheControl = forceFetch ? NO_CACHE : FORCE_CACHE;
        Timber.d(TAG, "fetch public feed cache strategy: " + cacheControl);
        return flickrApiService.fetchPublicFeed(cacheControl);
    }
}
