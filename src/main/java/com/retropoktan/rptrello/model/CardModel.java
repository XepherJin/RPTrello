package com.retropoktan.rptrello.model;

import com.google.gson.reflect.TypeToken;
import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.Card;
import com.retropoktan.rptrello.model.entity.Msg;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by RetroPoktan on 5/22/16.
 */
public class CardModel extends BaseModel {

    private final long boardId;

    public CardModel(long boardId, DataManager dataManager, Scheduler uiScheduler, Scheduler ioScheduler) {
        super(dataManager, uiScheduler, ioScheduler);
        this.boardId = boardId;
    }

    public Subscription getBoardCards(Subscriber<Msg<List<Card>>> subscriber) {
        return mDataManager.getClientApi()
                .getBoardCards(boardId)
                .subscribeOn(mIOScheduler)
                .observeOn(mUIScheduler)
                .subscribe(subscriber);
    }

    public void saveCardsForBoard(List<Card> list) {
        mDataManager.getDBHelper().refresh(Card.TAG, list);
    }

    public List<Card> getCachedCards() {
        return mDataManager.getDBHelper().get(Card.TAG, new TypeToken<List<Card>>() {
        }.getType());
    }
}
