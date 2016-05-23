package com.retropoktan.rptrello.ui.inject.component;

import com.retropoktan.rptrello.inject.component.ActivityComponent;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.activity.BoardDetailActivity;
import com.retropoktan.rptrello.ui.inject.module.CardModule;

import dagger.Component;

/**
 * Created by RetroPoktan on 5/22/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, CardModule.class})
public interface CardComponent extends ActivityComponent {
    void inject(BoardDetailActivity activity);
}
