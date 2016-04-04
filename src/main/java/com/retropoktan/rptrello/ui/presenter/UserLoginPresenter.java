package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.UserUsecase;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IUserLoginView;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView> {

    private final UserUsecase mUserUsecase;

    @Inject
    public UserLoginPresenter(UserUsecase userUsecase) {
        mUserUsecase = userUsecase;
    }

    public void login() {
        mView.loginSuccess();
    }

    private void validateInput(String phone, String password) {

    }

}
