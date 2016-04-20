package com.retropoktan.rptrello.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Button;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.model.entity.User;
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
import butterknife.OnTextChanged;

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
    }

    @OnTextChanged(R.id.et_register_name)
    void validateName(CharSequence text) {
        if (!TextUtils.isEmpty(name_layout.getEditText().getText())
                && !TextUtils.isEmpty(email_layout.getEditText().getText())
                && !TextUtils.isEmpty(password_layout.getEditText().getText())
                && !TextUtils.isEmpty(code_layout.getEditText().getText())) {
            actionEnabled();
            return;
        }
        actionDisabled();
    }

    @OnTextChanged(R.id.et_register_email)
    void validateEmail(CharSequence text) {
        if (!TextUtils.isEmpty(name_layout.getEditText().getText())
                && !TextUtils.isEmpty(email_layout.getEditText().getText())
                && !TextUtils.isEmpty(password_layout.getEditText().getText())
                && !TextUtils.isEmpty(code_layout.getEditText().getText())) {
            actionEnabled();
            return;
        }
        actionDisabled();
    }

    @OnTextChanged(R.id.et_register_password)
    void validatePassword(CharSequence text) {
        if (!TextUtils.isEmpty(name_layout.getEditText().getText())
                && !TextUtils.isEmpty(email_layout.getEditText().getText())
                && !TextUtils.isEmpty(password_layout.getEditText().getText())
                && !TextUtils.isEmpty(code_layout.getEditText().getText())) {
            actionEnabled();
            return;
        }
        actionDisabled();
    }

    @OnTextChanged(R.id.et_register_code)
    void validateCode(CharSequence text) {
        if (!TextUtils.isEmpty(name_layout.getEditText().getText())
                && !TextUtils.isEmpty(email_layout.getEditText().getText())
                && !TextUtils.isEmpty(password_layout.getEditText().getText())
                && !TextUtils.isEmpty(code_layout.getEditText().getText())) {
            actionEnabled();
            return;
        }
        actionDisabled();
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
        presenter.create();
    }

    @OnClick(R.id.btn_register_cancel)
    void cancel() {
        finish();
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
        btn_code.setEnabled(true);
    }

    @Override
    public void getCodeDisabled() {
        btn_code.setEnabled(false);
    }

    @Override
    public void invalidateSecond(long second) {
        btn_code.setText(second + " second(s)");
    }

    @Override
    public void resetCodeBtn() {
        btn_code.setText(R.string.action_get_code);
    }

    @Override
    public void showGetCodeError(String msg) {
        showToast(msg);
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
        btn_create.setEnabled(true);
        btn_create.setAlpha(1.0f);
    }

    @Override
    public void actionDisabled() {
        btn_create.setEnabled(false);
        btn_create.setAlpha(0.5f);
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
    public void loginSuccess(User user) {
        setResult(RESULT_OK);
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showLoadingError(CharSequence errMsg) {
        showToast(errMsg);
    }

    @Override
    protected void destroyPresenter() {
        presenter.onDestroy();
        presenter = null;
    }
}
