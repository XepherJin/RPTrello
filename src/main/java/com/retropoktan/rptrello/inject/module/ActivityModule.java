package com.retropoktan.rptrello.inject.module;

import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.base.BaseActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RetroPoktan on 12/25/15.
 */
@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    BaseActivity provideActivityContext() {
        return activity;
    }

}
