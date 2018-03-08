package com.nataliafavero.iddog.ui.feed;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nataliafavero.iddog.ui.menu.RecyclerViewClickListener;
import com.nataliafavero.iddog.ui.utils.Constants;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliafavero on 07/03/18.
 */

public class FeedFragment extends Fragment implements FeedContract.View, RecyclerViewClickListener {

    private FeedContract.Presenter mPresenter;
    private FeedAdapter adapter;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        List<Drawable> bitmaps = new ArrayList<>();
        adapter = new FeedAdapter(bitmaps, this);

        RecyclerView rv = new RecyclerView(getActivity());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        return rv;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        Intent intent = getActivity().getIntent();
        mPresenter.getFeed(intent.getStringExtra(Constants.KEY_TOKEN), intent.getStringExtra(Constants.CATEGORY));
    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showPhotos(String photos) {
        new LoadImageTask().execute(photos);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        System.out.println();
    }

    private class LoadImageTask extends AsyncTask<String, Void, Drawable> {

        protected Drawable doInBackground(String... urls) {
            String url = urls[0];
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                return Drawable.createFromStream(is, "src name");
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(Drawable result) {
            adapter.updatePhotos(result);
            adapter.notifyDataSetChanged();
        }
    }
}
