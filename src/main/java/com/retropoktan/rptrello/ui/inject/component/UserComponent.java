package com.retropoktan.rptrello.ui.inject.component;

import com.retropoktan.rptrello.inject.component.ActivityComponent;
import com.retropoktan.rptrello.inject.component.ApplicationComponent;
import com.retropoktan.rptrello.inject.module.ActivityModule;
import com.retropoktan.rptrello.inject.scope.PerActivity;
import com.retropoktan.rptrello.ui.activity.LoginActivity;
import com.retropoktan.rptrello.ui.activity.RegisterActivity;
import com.retropoktan.rptrello.ui.inject.module.UserModule;

import dagger.Component;

/**
 * Created by RetroPoktan on 2/10/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);
}
