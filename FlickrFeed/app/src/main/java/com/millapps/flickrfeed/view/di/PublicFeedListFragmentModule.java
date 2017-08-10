package com.millapps.flickrfeed.view.di;


import com.millapps.flickrfeed.model.PublicFeedDataModel;
import com.millapps.flickrfeed.model.network.RestApi;
import com.millapps.flickrfeed.view.PublicFeedListAdapter;
import com.millapps.flickrfeed.view.PublicFeedListFragment;
import com.millapps.flickrfeed.viewmodel.PublicFeedListViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PublicFeedListFragmentModule {

    @Provides
    public CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    public PublicFeedDataModel dataModel(RestApi restApi) {
        return new PublicFeedDataModel(restApi);
    }

    @Provides
    public PublicFeedListViewModel viewModel(PublicFeedListFragment fragment, PublicFeedDataModel dataModel, @Named("ioScheduler") Scheduler io, @Named("uiScheduler")Scheduler ui, CompositeDisposable compositeDisposable) {
        return new PublicFeedListViewModel(new PublicFeedListAdapter(fragment), dataModel, io, ui, compositeDisposable);
    }


}
