package com.millapps.flickrfeed;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class FlickrFeedApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return null;
    }
}
