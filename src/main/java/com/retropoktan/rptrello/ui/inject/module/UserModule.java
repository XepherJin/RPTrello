package com.retropoktan.rptrello.ui.inject.module;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.model.UserUsecase;
import com.retropoktan.rptrello.model.entity.User;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by RetroPoktan on 2/10/16.
 */
@Module
public class UserModule {

    @Provides
    @PerActivity
    UserUsecase provideUserUsecase(User user,
                                   DataManager dataManager,
                                   @Named("ui_thread") Scheduler ui,
                                   @Named("io_thread") Scheduler io) {
        return new UserUsecase(user, dataManager, ui, io);
    }
}
