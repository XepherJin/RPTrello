package com.retropoktan.rptrello.ui.inject.component;

import com.retropoktan.rptrello.inject.component.ActivityComponent;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.activity.BoardCreateActivity;
import com.retropoktan.rptrello.ui.fragment.AllBoardsFragment;
import com.retropoktan.rptrello.ui.inject.module.BoardModule;

import dagger.Component;

/**
 * Created by RetroPoktan on 4/21/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BoardModule.class})
public interface BoardComponent extends ActivityComponent {
    void inject(AllBoardsFragment fragment);

    void inject(BoardCreateActivity activity);
}