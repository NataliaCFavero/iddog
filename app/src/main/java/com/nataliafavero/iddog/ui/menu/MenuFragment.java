package com.nataliafavero.iddog.ui.menu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nataliafavero.iddog.ui.feed.FeedActivity;
import com.nataliafavero.iddog.ui.utils.Constants;

/**
 * Created by nataliafavero on 06/03/18.
 */

public class MenuFragment extends Fragment implements RecyclerViewClickListener {

    String[] dogs = {Category.HUSKY.toString(), Category.HOUND.toString(), Category.PUG.toString(), Category.LABRADOR.toString()};

    public MenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = new RecyclerView(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new MenuAdapter(dogs, getActivity(), this));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return rv;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(getActivity(), FeedActivity.class);
        intent.putExtra(Constants.CATEGORY, Category.values()[position].getCod());
        intent.putExtra(Constants.KEY_TOKEN, getActivity().getIntent().getStringExtra(Constants.KEY_TOKEN));
        getActivity().startActivity(intent);
    }
}
