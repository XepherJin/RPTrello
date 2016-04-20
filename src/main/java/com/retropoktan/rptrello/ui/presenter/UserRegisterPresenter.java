package com.retropoktan.rptrello.ui.presenter;

import android.os.CountDownTimer;

import com.retropoktan.rptrello.model.UserModel;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.model.req.UserCreateReq;
import com.retropoktan.rptrello.ui.presenter.base.BasePresenter;
import com.retropoktan.rptrello.ui.view.IUserRegisterView;
import com.retropoktan.rptrello.utils.MD5Util;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserRegisterPresenter extends BasePresenter<IUserRegisterView> {

    private final UserModel mUserModel;
    private CountDownTimer timer;

    @Inject
    public UserRegisterPresenter(UserModel UserModel) {
        mUserModel = UserModel;
    }

    public void getCode() {
        Subscription subscription = mUserModel.getCode(mView.getEmail(),
                new DefaultSubscriber<Msg>() {

                    @Override
                    protected void parseMsg(Msg msg) {
                        if (msg.isResultOK()) {
                            startCounting();
                            return;
                        }
                        mView.showGetCodeError(msg.getMsg());
                    }
                });
        addSubscription(subscription);
    }

    public void create() {
        mView.showLoading();
        UserCreateReq req = new UserCreateReq();
        req.setNick(mView.getUserName());
        req.setEmail(mView.getEmail());
        req.setCode(mView.getCode());
        req.setPassword(MD5Util.encode(mView.getPassword()));
        Subscription subscription = mUserModel.execCreate(req,
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

    private void startCounting() {
        if (timer == null) {
            timer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mView.getCodeDisabled();
                    mView.invalidateSecond(millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    mView.getCodeEnabled();
                    mView.resetCodeBtn();
                }
            };
        }
        timer.start();
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }
}
