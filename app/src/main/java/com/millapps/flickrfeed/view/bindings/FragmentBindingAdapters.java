package com.millapps.flickrfeed.view.bindings;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingComponent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class FragmentBindingAdapters implements DataBindingComponent {
    final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }

    @BindingAdapter("imageUrl")
    public void imageUrl(ImageView imageView, String url) {
        Glide.with(fragment)
                .load(url)
                .apply(fitCenterTransform())
                .into(imageView);

    }

    @Override
    public FragmentBindingAdapters getFragmentBindingAdapters() {
        return this;
    }
}
