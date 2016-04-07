package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;

import rx.Scheduler;

/**
 * Created by RetroPoktan on 12/18/15.
 */
public abstract class BaseModel {

    protected final Scheduler mIOScheduler;
    protected final Scheduler mUIScheduler;
    protected DataManager mDataManager;

    public BaseModel(DataManager dataManager, Scheduler uiScheduler,
                   Scheduler ioScheduler) {
        mDataManager = dataManager;
        mIOScheduler = ioScheduler;
        mUIScheduler = uiScheduler;
    }

}