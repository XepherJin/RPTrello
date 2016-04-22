package com.retropoktan.rptrello.ui.inject.component;

import com.retropoktan.rptrello.inject.component.ActivityComponent;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.fragment.AllTasksFragment;
import com.retropoktan.rptrello.ui.inject.module.TaskModule;

import dagger.Component;

/**
 * Created by RetroPoktan on 4/22/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, TaskModule.class})
public interface TaskComponent extends ActivityComponent {
    void inject(AllTasksFragment fragment);
}