package com.retropoktan.rptrello.ui.inject.component;

import com.retropoktan.rptrello.inject.component.ActivityComponent;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.fragment.AllTeamsFragment;
import com.retropoktan.rptrello.ui.inject.module.TeamModule;

import dagger.Component;

/**
 * Created by RetroPoktan on 4/21/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TeamModule.class})
public interface TeamComponent extends ActivityComponent {
    void inject(AllTeamsFragment fragment);
}
