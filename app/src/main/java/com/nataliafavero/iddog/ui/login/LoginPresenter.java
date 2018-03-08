package com.nataliafavero.iddog.ui.login;

import android.support.annotation.NonNull;

import com.nataliafavero.iddog.data.model.SignupRequest;
import com.nataliafavero.iddog.data.model.SignupResponse;
import com.nataliafavero.iddog.data.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nataliafavero on 05/03/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;
    private final ApiService service;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginView.setPresenter(this);
        service = new ApiService();
    }

    @Override
    public void start() {

    }

    @Override
    public void doLogin(String email) {
        service.getApi()
                .signup(new SignupRequest(email))
                .enqueue(new Callback<SignupResponse>() {
                    @Override
                    public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                        if (response.code() == 200) {
                            SignupResponse result = response.body();

                            if (!result.getUser().getToken().isEmpty()) {
                                //TODO salvar no shared_preferences o token
                                mLoginView.startDogsList(result.getUser().getToken());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupResponse> call, Throwable t) {
                        //TODO chamar tela para exibir erro

                    }
                });

    }
}
