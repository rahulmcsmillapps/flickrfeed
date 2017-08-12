package com.millapps.flickrfeed.view;

import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class PublicFeedListActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, PublicFeedListFragment.newInstance(), PublicFeedListFragment.class.getName())
                    .commit();
        }
    }

}
