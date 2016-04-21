package com.retropoktan.rptrello.ui.inject.module;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.model.BoardModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by RetroPoktan on 4/21/16.
 */
@Module
public class BoardModule {

    @Provides
    @PerActivity
    BoardModel provideBoardModel(DataManager dataManager,
                                 @Named("ui_thread") Scheduler ui,
                                 @Named("io_thread") Scheduler io) {
        return new BoardModel(dataManager, ui, io);
    }

}
