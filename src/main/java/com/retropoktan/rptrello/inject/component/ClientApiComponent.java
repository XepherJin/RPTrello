package com.retropoktan.rptrello.inject.component;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.module.ClientApiModule;
import com.retropoktan.rptrello.inject.scope.PerDataManager;

import dagger.Component;

/**
 * Created by RetroPoktan on 4/21/16.
 */
@PerDataManager
@Component(dependencies = DataManagerComponent.class, modules = ClientApiModule.class)
public interface ClientApiComponent {
    void inject(DataManager dataManager);
}
