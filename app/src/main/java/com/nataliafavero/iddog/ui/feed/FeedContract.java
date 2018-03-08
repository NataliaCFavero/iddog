package com.nataliafavero.iddog.ui.feed;

import com.nataliafavero.iddog.ui.base.BasePresenter;
import com.nataliafavero.iddog.ui.base.BaseView;
import com.nataliafavero.iddog.ui.login.LoginContract;

/**
 * Created by nataliafavero on 07/03/18.
 */

public interface FeedContract {

    interface View extends BaseView<FeedContract.Presenter> {
        public void showLoading();
        public void showPhotos(String photos);
    }

    interface Presenter extends BasePresenter {
        public void getFeed(String token, String category);
    }
}
