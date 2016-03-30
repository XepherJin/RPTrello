package com.retropoktan.rptrello.inject.component;

import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.base.BaseActivity;

import dagger.Component;

/**
 * Created by RetroPoktan on 12/25/15.
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    BaseActivity getActivity();
}
