package com.millapps.flickrfeed.view.di;

import com.millapps.flickrfeed.view.PublicFeedListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PublicFeedListActivityProvider {

    @ContributesAndroidInjector(modules = PublicFeedListFragmentProvider.class)
    abstract PublicFeedListActivity bindPublicFeedListActivity();
}
