package com.retropoktan.rptrello.ui.activity;

import com.retropoktan.rptrello.R;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.ui.base.BaseActivity;
import com.retropoktan.rptrello.ui.inject.component.DaggerUserComponent;
import com.retropoktan.rptrello.ui.inject.module.UserModule;
import com.retropoktan.rptrello.ui.presenter.UserRegisterPresenter;
import com.retropoktan.rptrello.ui.view.IUserRegisterView;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 2/5/16.
 */
public class RegisterActivity extends BaseActivity implements IUserRegisterView {

    @Inject
    UserRegisterPresenter presenter;

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

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void userNameError() {

    }

    @Override
    public String getPhoneNum() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void actionEnabled() {

    }

    @Override
    public void actionDisabled() {

    }

    @Override
    public void phoneNumError() {

    }

    @Override
    public void passwordError() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoadingError() {

    }
}
