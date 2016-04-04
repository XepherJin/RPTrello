package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.User;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 2/10/16.
 */
public class UserUsecase extends Usecase {

    private User mUser;

    public UserUsecase(User user, DataManager dataManager,
                       Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
        mUser = user;
    }

    public Subscription execLogin(Subscriber subscriber) {
        return subscriber;
    }

}
