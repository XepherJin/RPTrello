package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.User;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserModel extends BaseModel {

    private User mUser;

    public UserModel(User user, DataManager dataManager,
                       Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
        mUser = user;
    }

    public Subscription execLogin(Subscriber subscriber) {
        return subscriber;
    }

    public void getCode() {
        mDataManager.getClientApi().getCode("575508330@qq.com")
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
    }

}
