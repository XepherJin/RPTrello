package com.retropoktan.rptrello.ui.inject.module;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.model.TaskModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by RetroPoktan on 4/22/16.
 */
@Module
public class TaskModule {

    @Provides
    @PerActivity
    TaskModel provideBoardModel(DataManager dataManager,
                                @Named("ui_thread") Scheduler ui,
                                @Named("io_thread") Scheduler io) {
        return new TaskModel(dataManager, ui, io);
    }
}
