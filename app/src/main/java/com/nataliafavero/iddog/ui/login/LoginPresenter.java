package com.nataliafavero.iddog.ui.login;

import android.support.annotation.NonNull;

/**
 * Created by nataliafavero on 05/03/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void doLogin(String email) {

    }
}
