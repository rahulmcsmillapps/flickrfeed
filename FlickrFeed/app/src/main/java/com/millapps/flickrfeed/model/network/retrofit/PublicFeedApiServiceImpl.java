package com.millapps.flickrfeed.model.network.retrofit;

import com.millapps.flickrfeed.model.entity.PublicFeed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PublicFeedApiServiceImpl {
    @GET("/services/feeds/photos_public.gne?format=json&nojsoncallback=true")
    Observable<PublicFeed> fetchPublicFeed(@Header("cache-control") String cacheControl);

}
