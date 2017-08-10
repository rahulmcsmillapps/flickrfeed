package com.millapps.flickrfeed;

import android.app.Application;

import com.millapps.flickrfeed.di.DaggerAppComponent;
import com.millapps.flickrfeed.utils.RxUtils;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class FlickrFeedApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        RxUtils.initRxJava2ErrorHandler();
        return DaggerAppComponent.builder().create(this);
    }
}
