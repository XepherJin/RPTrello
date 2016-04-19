package com.retropoktan.rptrello.inject.module;

import com.retropoktan.rptrello.TrelloApplication;
import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.model.entity.User;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by RetroPoktan on 12/12/15.
 */
@Module
public class ApplicationModule {

    private final TrelloApplication mApplication;

    public ApplicationModule(TrelloApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    TrelloApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager(mApplication);
    }

    @Provides
    @Singleton
    User provideUser() {
        return new User();
    }

    @Provides
    @Named("io_thread")
    Scheduler provideIOThread() {
        return Schedulers.io();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUIThread() {
        return AndroidSchedulers.mainThread();
    }
}
