package com.retropoktan.rptrello.inject.module;

import android.content.Context;

import com.retropoktan.rptrello.inject.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RetroPoktan on 12/25/15.
 */
@Module
public class ActivityModule {

    private final Context activity;

    public ActivityModule(Context activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Context provideActivityContext() {
        return activity;
    }

}
