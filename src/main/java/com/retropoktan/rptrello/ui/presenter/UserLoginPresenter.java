package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.UserModel;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IUserLoginView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserLoginPresenter extends BasePresenter<IUserLoginView> {

    private final UserModel mUserModel;

    @Inject
    public UserLoginPresenter(UserModel UserModel) {
        mUserModel = UserModel;
    }

    public void login() {
        addSubscription(mUserModel
                .execLogin(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        removeSubscription(this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        removeSubscription(this);
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                }));
        mView.loginSuccess();
    }

    private void validateInput(String phone, String password) {

    }

}
