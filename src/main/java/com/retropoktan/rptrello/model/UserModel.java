package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.model.req.UserCreateReq;
import com.retropoktan.rptrello.model.req.UserLoginReq;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserModel extends BaseModel {

    private User mUser;

    public UserModel(DataManager dataManager,
                       Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
        mUser = mDataManager.getUser();
    }

    public Subscription execLogin(UserLoginReq req, Subscriber subscriber) {
        return mDataManager.getClientApi().login(req)
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public Subscription logout(Subscriber subscriber) {
        return mDataManager.getClientApi().logout()
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public Subscription getCode(String email, Subscriber<Msg> subscriber) {
        return mDataManager.getClientApi().getCode(email)
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public Subscription execCreate(UserCreateReq req, Subscriber<Msg<User>> subscriber) {
        return mDataManager.getClientApi().createAccount(req)
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public void saveUser(User user) {
        mUser.setEmail(user.getEmail());
        mUser.setAvatar(user.getAvatar());
        mUser.setId(user.getId());
        mUser.setNick(user.getNick());
        mUser.setToken(user.getToken());
        mDataManager.setToken(user.getToken());
        mDataManager.getDBHelper().refresh(User.TAG, mUser);
    }

    public User getUser() {
        return mUser;
    }

    public void deleteUser() {
        mUser.setId(0L);
        mUser.setToken(null);
        mUser.setAvatar(null);
        mUser.setEmail(null);
        mUser.setNick(null);
        mDataManager.getDBHelper().refresh(User.TAG, mUser);
    }

}
