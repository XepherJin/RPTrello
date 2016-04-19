package com.retropoktan.rptrello.inject.component;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.module.ApplicationModule;
import com.retropoktan.rptrello.model.entity.User;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

/**
 * Created by RetroPoktan on 12/13/15.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    User getUser();

    DataManager getDataManager();

    @Named("ui_thread")
    Scheduler getUIThread();

    @Named("io_thread")
    Scheduler getIOThread();
}
