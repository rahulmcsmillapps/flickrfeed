package com.millapps.flickrfeed.di;

import com.millapps.flickrfeed.FlickrFeedApplication;
import com.millapps.flickrfeed.view.di.PublicFeedListActivityProvider;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        PublicFeedListActivityProvider.class,
        NetworkModule.class
})
public interface AppComponent extends AndroidInjector<FlickrFeedApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<FlickrFeedApplication> {
    }
}

