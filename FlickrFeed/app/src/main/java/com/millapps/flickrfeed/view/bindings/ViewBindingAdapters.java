package com.millapps.flickrfeed.view.bindings;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewBindingAdapters {
    @BindingAdapter("adapter")
    public static void adapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("divider")
    public static void divider(RecyclerView recyclerView, int orientation) {
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), orientation));
    }

    @BindingAdapter("gone")
    public static void gone(View view, boolean gone) {
        view.setVisibility(gone?View.GONE:View.VISIBLE);
    }

}
