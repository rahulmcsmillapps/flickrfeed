package com.millapps.flickrfeed.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.millapps.flickrfeed.model.PublicFeedDataModel;
import com.millapps.flickrfeed.utils.RxUtils;
import com.millapps.flickrfeed.view.PublicFeedListAdapter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class PublicFeedListViewModel extends BaseObservable {

    private static String TAG = PublicFeedListViewModel.class.getName();
    private PublicFeedListAdapter adapter;
    private boolean loading;
    private boolean showSyncError;
    private PublicFeedDataModel dataModel;
    private Scheduler io, ui;
    private CompositeDisposable compositeDisposable;
    private String syncTime;

    @Inject
    public PublicFeedListViewModel(PublicFeedListAdapter adapter, PublicFeedDataModel dataModel, Scheduler io, Scheduler ui, CompositeDisposable compositeDisposable) {
        this.adapter = adapter;
        this.dataModel = dataModel;
        this.io = io;
        this.ui = ui;
        this.compositeDisposable = compositeDisposable;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Bindable
    public boolean getLoading() {
        return loading;
    }

    @Bindable
    public String getSyncTime() {
        return syncTime;
    }

    @Bindable
    public boolean getShowSyncError() {
        return showSyncError;
    }

    public void loadPublicFeed() {
        Disposable disposable = dataModel.publicFeed()
                .doOnSubscribe(ignoredDisposable -> setLoading(true))
                .doOnTerminate(() -> setLoading(false))
                .compose(RxUtils.applyIOSchedulers(io, ui, true))
                .subscribe(publicFeedItems -> {
                            Timber.d(TAG, "refreshing PublicFeedItems");
                            adapter.setData(publicFeedItems.getItems());
                            setSyncTime(publicFeedItems.getModified());
                            setShowSyncError(false);
                        }, throwable -> {
                            Timber.d(TAG, throwable);
                            setShowSyncError(true);
                        }
                );
        compositeDisposable.add(disposable);
    }

    private void setLoading(boolean loading) {
        this.loading = loading;
        notifyPropertyChanged(BR.loading);
    }

    private void setSyncTime(String syncTime) {
        if (syncTime != null) {
            this.syncTime = syncTime;
            notifyPropertyChanged(BR.syncTime);
        }
    }

    private void setShowSyncError(boolean showSyncError) {
        this.showSyncError = showSyncError;
        notifyPropertyChanged(BR.showSyncError);
    }

}