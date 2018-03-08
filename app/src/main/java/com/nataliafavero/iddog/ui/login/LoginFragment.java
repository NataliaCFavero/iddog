package com.nataliafavero.iddog.ui.login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.nataliafavero.iddog.R;
import com.nataliafavero.iddog.ui.menu.MenuActivity;
import com.nataliafavero.iddog.ui.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nataliafavero on 05/03/18.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    @BindView(R.id.login_input_email)
    EditText mInputEmail;

    @BindView(R.id.login_layout_email)
    TextInputLayout mLayoutEmail;

    @BindView(R.id.login_btn_signgup)
    Button mBtnSignup;

    private LoginContract.Presenter mPresenter;
    private ProgressDialog progress;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoading() {
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //progress.setContentView(R.layout.progress_dialog);
        progress.getWindow().setGravity(Gravity.CENTER);
        progress.show();
    }

    @Override
    public void startDogsList(String token) {
        progress.hide();
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        intent.putExtra(Constants.KEY_TOKEN, token);
        getActivity().startActivity(intent);
    }

    private final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @OnClick(value = R.id.login_btn_signgup)
    public void onClickButtonSignup() {
        if (!isValidEmail(mInputEmail.getText())) {
            mLayoutEmail.setHint(getResources().getString(R.string.invalid_email));
        } else {
            showLoading();
            mPresenter.doLogin(mInputEmail.getText().toString());
        }

    }
}
