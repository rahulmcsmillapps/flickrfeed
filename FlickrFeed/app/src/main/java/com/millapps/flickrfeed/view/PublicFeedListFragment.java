package com.millapps.flickrfeed.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.millapps.flickrfeed.R;
import com.millapps.flickrfeed.databinding.FragmentFlickrFeedListBinding;
import com.millapps.flickrfeed.viewmodel.PublicFeedListViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.CompositeDisposable;

public class PublicFeedListFragment extends DaggerFragment {
    @Inject
    PublicFeedListViewModel viewModel;

    @Inject
    CompositeDisposable compositeDisposable;

    public static PublicFeedListFragment newInstance() {
        return new PublicFeedListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentFlickrFeedListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flickr_feed_list, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.loadPublicFeed();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

}
