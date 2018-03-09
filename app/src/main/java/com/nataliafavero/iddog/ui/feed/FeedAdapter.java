package com.nataliafavero.iddog.ui.feed;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nataliafavero.iddog.R;
import com.nataliafavero.iddog.ui.menu.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliafavero on 07/03/18.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<Drawable> dataSource;
    private static RecyclerViewClickListener itemListener;

    public FeedAdapter(RecyclerViewClickListener itemListener) {
        this.itemListener = itemListener;
    }

    public void updatePhotos(Drawable image) {
        if (dataSource == null) {
            dataSource = new ArrayList<>();
        }
        dataSource.add(image);

    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public FeedViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_pet);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getAdapterPosition());
        }
    }


    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FeedAdapter.FeedViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false));


    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        if (dataSource != null) {
            holder.imageView.setImageDrawable(dataSource.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if (dataSource != null) {
            return dataSource.size();
        } else {
            return 0;
        }
    }
}
