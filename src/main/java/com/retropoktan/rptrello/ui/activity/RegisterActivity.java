package com.retropoktan.rptrello.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.inject.component.DaggerUserComponent;
import com.retropoktan.rptrello.ui.inject.module.UserModule;
import com.retropoktan.rptrello.ui.presenter.UserRegisterPresenter;
import com.retropoktan.rptrello.ui.view.IUserRegisterView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by RetroPoktan on 2/5/16.
 */
public class RegisterActivity extends BaseActivity implements IUserRegisterView {

    @Inject
    UserRegisterPresenter presenter;
    @Bind(R.id.et_register_name_layout)
    TextInputLayout name_layout;
    @Bind(R.id.et_register_email_layout)
    TextInputLayout email_layout;
    @Bind(R.id.et_register_password_layout)
    TextInputLayout password_layout;
    @Bind(R.id.et_register_code_layout)
    TextInputLayout code_layout;
    @Bind(R.id.btn_register_cancel)
    Button btn_cancel;
    @Bind(R.id.btn_register_create)
    Button btn_create;
    @Bind(R.id.btn_get_code)
    Button btn_code;

    @BindString(R.string.error_invalid_name)
    String errorName;
    @BindString(R.string.error_invalid_email)
    String errorEmail;
    @BindString(R.string.error_invalid_password)
    String invalidPassword;
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
    }

    private void initViews() {

    }

    @Override
    protected int getRootContentView() {
        return R.layout.activity_register;
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

    @OnClick(R.id.btn_register_create)
    void create() {

    }

    @OnClick(R.id.btn_register_cancel)
    void cancel() {

    }


    @Override
    public String getUserName() {
        return name_layout.getEditText().getText().toString().trim();
    }

    @Override
    public void userNameError() {

    }

    @Override
    public String getCode() {
        return code_layout.getEditText().getText().toString().trim();
    }

    @Override
    public void getCodeEnabled() {

    }

    @Override
    public void getCodeDisabled() {

    }

    @Override
    public void invalidateSecond(int second) {

    }

    @Override
    public String getEmail() {
        return email_layout.getEditText().getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password_layout.getEditText().getText().toString().trim();
    }

    @Override
    public void actionEnabled() {

    }

    @Override
    public void actionDisabled() {

    }

    @Override
    public void emailError() {
        email_layout.setError(errorEmail);
    }

    @Override
    public void passwordError() {
        password_layout.setError(invalidPassword);
    }

    @Override
    public void loginSuccess() {

    }

    @OnClick(R.id.btn_get_code)
    void getVerifyCode() {
        presenter.getCode();
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
