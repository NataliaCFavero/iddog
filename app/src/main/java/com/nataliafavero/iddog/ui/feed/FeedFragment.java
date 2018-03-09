package com.nataliafavero.iddog.ui.feed;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nataliafavero.iddog.R;
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
    private List<Drawable> images;
    private ProgressDialog progress;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        images = new ArrayList<>();
        adapter = new FeedAdapter(this);

        RecyclerView rv = new RecyclerView(getActivity());
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        return rv;
    }

    @Override
    public void onResume() {
        super.onResume();
        showLoading();
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
        progress = new ProgressDialog(getActivity());
        progress.show();
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.setContentView(R.layout.progress_dialog);

    }

    @Override
    public void showPhotos(String photos) {
        new LoadImageTask().execute(photos);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(images.get(position));

        Dialog dialog = new Dialog(getContext())
        {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                this.dismiss();
                return true;
            }
        };
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);


        dialog.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.getWindow().setAttributes(lp);
        dialog.show();

    }

    private class LoadImageTask extends AsyncTask<String, Void, Drawable> {

        protected Drawable doInBackground(String... urls) {
            String url = urls[0];
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable drawable = Drawable.createFromStream(is, "IDDOG");
                images.add(drawable);
                return drawable;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Drawable result) {
            if (result != null) {
                if(progress!=null && progress.isShowing()) {
                    progress.hide();
                }
                adapter.updatePhotos(result);
                adapter.notifyDataSetChanged();
            }

        }
    }
}
