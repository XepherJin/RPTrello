package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Team;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class TeamModel extends BaseModel {

    public TeamModel(DataManager dataManager, Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
    }

    public Subscription getAllTeams(Subscriber<Msg<List<Team>>> subscriber) {
        return mDataManager.getClientApi().getTeams()
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public void saveAllTeams(List<Team> list) {
        mDataManager.getDBHelper().refresh(Team.TAG, list);
    }

    public List<Team> getCachedTeams() {
        return null;
    }

}
