package com.millapps.flickrfeed.view.di;

import com.millapps.flickrfeed.view.PublicFeedListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PublicFeedListFragmentProvider {
    @ContributesAndroidInjector(modules = PublicFeedListFragmentModule.class)
    abstract PublicFeedListFragment providePublicFeedListFragment();
}