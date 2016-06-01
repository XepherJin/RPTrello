package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Task;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 4/22/16.
 */
public class TaskModel extends BaseModel {

    public TaskModel(DataManager dataManager, Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
    }

    public Subscription getAllTasks(Subscriber<Msg<List<Task>>> subscriber) {
        return mDataManager.getClientApi().getTasks()
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public Subscription getCardTasks(long cardId, Subscriber<Msg<List<Task>>> subscriber) {
        return mDataManager.getClientApi().getCardTasks(cardId)
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public void saveAllTasks(List<Task> list) {
        mDataManager.getDBHelper().refresh(Task.TAG, list);
    }

    public List<Task> getCachedTasks() {
        return null;
    }
}
