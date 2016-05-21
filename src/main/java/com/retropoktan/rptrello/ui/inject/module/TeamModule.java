package com.retropoktan.rptrello.ui.inject.module;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.model.TeamModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by RetroPoktan on 4/21/16.
 */
@Module
public class TeamModule {

    @Provides
    @PerActivity
    TeamModel provideTeamModel(DataManager dataManager,
                               @Named("ui_thread") Scheduler ui,
                               @Named("io_thread") Scheduler io) {
        return new TeamModel(dataManager, ui, io);
    }
}
