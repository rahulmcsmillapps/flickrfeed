package com.millapps.flickrfeed.view.bindings;

import android.databinding.BindingAdapter;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.millapps.flickrfeed.R;

public class ViewBindingAdapters {
    @BindingAdapter("adapter")
    public static void adapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("divider")
    public static void divider(RecyclerView recyclerView, int orientation) {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), orientation));
    }

    @BindingAdapter("syncErrorSnackBar")
    public static void snackbar(View view, boolean show) {
        if (show) {
            Snackbar.make(view, R.string.error_feed_sync, Snackbar.LENGTH_LONG).show();
        }
    }


    @BindingAdapter("gone")
    public static void gone(View view, boolean gone) {
        view.setVisibility(gone?View.GONE:View.VISIBLE);
    }

}
