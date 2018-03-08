package com.nataliafavero.iddog.ui.feed;

import android.support.annotation.NonNull;

import com.nataliafavero.iddog.data.model.FeedResponse;
import com.nataliafavero.iddog.data.service.ApiService;
import com.nataliafavero.iddog.ui.login.LoginContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nataliafavero on 07/03/18.
 */

public class FeedPresenter implements FeedContract.Presenter {

    private final FeedContract.View mView;
    private final ApiService service;

    public FeedPresenter(@NonNull FeedContract.View feedView) {
        mView = feedView;
        mView.setPresenter(this);
        service = new ApiService();
    }

    @Override
    public void start() {

    }

    @Override
    public void getFeed(String token, String category) {
        service.getApi()
                .feed(token, category)
                .enqueue(new Callback<FeedResponse>() {
                    @Override
                    public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                        if (response.code() == 200) {
                            for (int i =0; i< response.body().getImages().length; i++ ) {
                                mView.showPhotos(response.body().getImages()[i]);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<FeedResponse> call, Throwable t) {

                    }
                });
    }
}
