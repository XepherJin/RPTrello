package com.retropoktan.rptrello.ui.inject.module;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.model.CardModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by RetroPoktan on 5/22/16.
 */
@Module
public class CardModule {

    private long boardId = -1;

    public CardModule() {

    }

    public CardModule(long boardId) {
        this.boardId = boardId;
    }

    @Provides
    @PerActivity
    CardModel provideCardModel(DataManager dataManager,
                               @Named("ui_thread") Scheduler ui,
                               @Named("io_thread") Scheduler io) {
        return new CardModel(boardId, dataManager, ui, io);
    }

}
