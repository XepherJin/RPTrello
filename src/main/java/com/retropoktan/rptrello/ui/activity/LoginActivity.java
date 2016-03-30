package com.retropoktan.rptrello.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.inject.component.DaggerUserComponent;
import com.retropoktan.rptrello.ui.inject.module.UserModule;
import com.retropoktan.rptrello.ui.presenter.UserLoginPresenter;
import com.retropoktan.rptrello.ui.view.IUserLoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RetroPoktan on 12/19/15.
 */
public class LoginActivity extends BaseActivity implements IUserLoginView {

    @Inject
    UserLoginPresenter presenter;
    @Bind(R.id.et_login_phone_layout)
    TextInputLayout phone_layout;
    @Bind(R.id.et_login_password_layout)
    TextInputLayout password_layout;
    @Bind(R.id.btn_login_cancel)
    Button btn_cancel;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.iv_forget_password)
    ImageView iv_forget_password;

    @BindString(R.string.error_invalid_phone)
    String errorPhone;
    @BindString(R.string.error_incorrect_password)
    String wrongPassword;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.error_network_unavailable)
    String networkUnavailable;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViews();
        actionEnabled();
    }

    private void initViews() {
        iv_forget_password.setColorFilter(ContextCompat.getColor(this, R.color.textColorHint));
    }

    @Override
    protected int getRootContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupComponent() {
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .userModule(new UserModule())
                .build()
                .inject(this);
    }

    @Override
    protected void attachViewForPresenter() {
        presenter.attachView(this);
    }

    @OnClick(R.id.btn_login)
    void login() {
        presenter.login();
    }

    @OnClick(R.id.btn_login_cancel)
    void cancel() {
        finish();
    }

    @Override
    public String getPhoneNum() {
        return phone_layout.getEditText().getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password_layout.getEditText().getText().toString().trim();
    }

    @Override
    public void actionEnabled() {
        btn_login.setEnabled(true);
        btn_login.setAlpha(1.0f);
    }

    @Override
    public void actionDisabled() {
        btn_login.setEnabled(false);
        btn_login.setAlpha(0.5f);
    }

    @Override
    public void phoneNumError() {
        phone_layout.setError(errorPhone);
    }

    @Override
    public void passwordError() {
        phone_layout.setError(wrongPassword);
    }

    @Override
    public void loginSuccess() {
        setResult(RESULT_OK);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(loading);
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showLoadingError() {
    }

    @Override
    protected void destroyPresenter() {
        presenter.onDestroy();
        presenter = null;
    }
}
