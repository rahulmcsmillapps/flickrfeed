package com.millapps.flickrfeed.view;

import android.content.Context;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.millapps.flickrfeed.R;
import com.millapps.flickrfeed.databinding.FlickrFeedListItemBinding;
import com.millapps.flickrfeed.model.entity.PublicFeedItem;
import com.millapps.flickrfeed.view.bindings.FragmentBindingAdapters;
import com.millapps.flickrfeed.viewmodel.PublicFeedListItemViewModel;

import java.util.List;

public class PublicFeedListAdapter extends RecyclerView.Adapter<PublicFeedListAdapter.ViewHolder> {
    private DataBindingComponent dataBindingComponent;
    private Context context;
    private List<PublicFeedItem> publicFeedItemList;

    public PublicFeedListAdapter(Fragment fragment) {
        dataBindingComponent = new FragmentBindingAdapters(fragment);
        context = fragment.getContext();
    }

    public void setData(List<PublicFeedItem> publicFeedItemList) {
        if (publicFeedItemList != null && publicFeedItemList.size() > 0) {
            this.publicFeedItemList = publicFeedItemList;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FlickrFeedListItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.flickr_feed_list_item, parent, false, dataBindingComponent);

        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(publicFeedItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return publicFeedItemList == null ? 0 : publicFeedItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private FlickrFeedListItemBinding itemBinding;

        ViewHolder(FlickrFeedListItemBinding itemBinding) {
            super(itemBinding.getRoot());

            this.itemBinding = itemBinding;
        }

        void bind(PublicFeedItem publicFeedItem) {
            itemBinding.setViewModel(new PublicFeedListItemViewModel(publicFeedItem));
            itemBinding.executePendingBindings();
        }
    }

}