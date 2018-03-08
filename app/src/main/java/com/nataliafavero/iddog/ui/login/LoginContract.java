package com.nataliafavero.iddog.ui.login;

import com.nataliafavero.iddog.ui.base.BasePresenter;
import com.nataliafavero.iddog.ui.base.BaseView;

/**
 * Created by nataliafavero on 05/03/18.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        public void showLoading();
        public void startDogsList(String token);
    }

    interface Presenter extends BasePresenter {
        public void doLogin(String email);
    }
}
