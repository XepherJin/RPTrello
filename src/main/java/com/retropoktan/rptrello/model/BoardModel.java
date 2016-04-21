package com.retropoktan.rptrello.model;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Msg;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class BoardModel extends BaseModel {

    public BoardModel(DataManager dataManager, Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
    }

    public Subscription getAllBoards(Subscriber<Msg<List<Board>>> subscriber) {
        return mDataManager.getClientApi().getBoards()
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public void saveAllBoards(List<Board> list) {
        mDataManager.getDBHelper().refresh(Board.TAG, list);
    }

    public List<Board> getCachedBoards() {
        return null;
    }
}
