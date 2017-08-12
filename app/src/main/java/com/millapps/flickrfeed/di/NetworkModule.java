package com.millapps.flickrfeed.di;

import android.content.Context;

import com.millapps.flickrfeed.BuildConfig;
import com.millapps.flickrfeed.FlickrFeedApplication;
import com.millapps.flickrfeed.model.network.RestApi;
import com.millapps.flickrfeed.model.network.retrofit.NetworkRestApi;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final String FLICKR_FEED_HOST = "https://api.flickr.com";
    private static final String CACHE_FILE_NAME = "flickrfeed_http_response_cache";
    private static final int CACHE_FILE_SIZE = 10 * 1024 * 1024; // 10 MB

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(FlickrFeedApplication application) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        //install Http Response cache and force response to be cached for around 4 weeks
        //so that stale data(if available in cache) can be shown while fresh data is being loaded.
        //We would ideally cache this in a more efficient reactive db like Realm but for now this is a
        // good enough solution.
        builder.addNetworkInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request).newBuilder()
                    .removeHeader("pragma")
                    .removeHeader("cache-control")
                    .header("cache-control", "public, max-age=2419200")
                    .build();

        });

        Cache cache = new Cache(getCacheDirectory(application, CACHE_FILE_NAME), CACHE_FILE_SIZE);
        return builder.cache(cache)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(FLICKR_FEED_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public RestApi provideRestApi(NetworkRestApi networkRestApi) {
        return networkRestApi;
    }

    @Provides
    @Named("ioScheduler")
    public Scheduler providesIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("uiScheduler")
    public Scheduler providesUiScheduler() {
        return AndroidSchedulers.mainThread();
    }

    private static File getCacheDirectory(Context context, String fileName) {
        File cacheDir = new File(context.getCacheDir(), fileName);
        return cacheDir;
    }

}

