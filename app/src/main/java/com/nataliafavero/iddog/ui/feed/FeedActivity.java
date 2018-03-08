package com.nataliafavero.iddog.ui.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nataliafavero.iddog.R;
import com.nataliafavero.iddog.ui.utils.ActivityUtils;

/**
 * Created by nataliafavero on 07/03/18.
 */

public class FeedActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager().findFragmentById(R.id.activity_feed_frame);

        if (feedFragment == null) {
            feedFragment = FeedFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), feedFragment, R.id.activity_feed_frame);
        }

        new FeedPresenter(feedFragment);
    }
}
