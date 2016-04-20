package com.retropoktan.rptrello.ui.presenter;

import com.retropoktan.rptrello.model.UserModel;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.model.req.UserLoginReq;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IUserLoginView;
import com.retropoktan.rptrello.utils.MD5Util;

import javax.inject.Inject;

import rx.Subscription;

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
        mView.showLoading();
        UserLoginReq req = new UserLoginReq();
        req.setEmail(mView.getEmail());
        req.setPassword(MD5Util.encode(mView.getPassword()));
        Subscription subscription = mUserModel.execLogin(req,
                new DefaultSubscriber<Msg<User>>() {

                    @Override
                    protected void parseMsg(Msg<User> msg) {
                        if (msg.isResultOK()) {
                            User user = msg.getData();
                            if (user != null) {
                                mUserModel.saveUser(user);
                                mView.loginSuccess(user);
                                return;
                            }
                            mView.showLoadingError(msg.getMsg());
                            return;
                        }
                        mView.showLoadingError(msg.getMsg());
                    }
                });
        addSubscription(subscription);
    }

}
