package com.retropoktan.rptrello;

import android.app.Application;

import com.anupcowkur.reservoir.Reservoir;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.component.DaggerApplicationComponent;
import com.retropoktan.rptrello.inject.module.ApplicationModule;

/**
 * Created by RetroPoktan on 11/30/15.
 */
public class TrelloApplication extends Application {

    private static final long DATA_CACHE_SIZE = 1024L * 1024L * 3L;
    private static TrelloApplication instance;
    private ApplicationComponent mApplicationComponent;

    public static TrelloApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initInjection();
        try {
            Reservoir.init(this, DATA_CACHE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initInjection() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
