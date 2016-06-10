package com.retropoktan.rptrello.ui.inject.component;

import com.retropoktan.rptrello.inject.component.ActivityComponent;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.activity.BoardCreateActivity;
import com.retropoktan.rptrello.ui.inject.module.BoardModule;
import com.retropoktan.rptrello.ui.inject.module.TeamModule;

import dagger.Component;

/**
 * Created by RetroPoktan on 6/10/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BoardModule.class, TeamModule.class})
public interface BoardCreateComponent extends ActivityComponent {
    void inject(BoardCreateActivity activity);
}
