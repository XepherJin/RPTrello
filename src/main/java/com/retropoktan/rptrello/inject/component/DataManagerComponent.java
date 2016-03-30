package com.retropoktan.rptrello.inject.component;

import com.retropoktan.rptrello.data.DataManager;
import com.retropoktan.rptrello.inject.module.ClientApiModule;
import com.retropoktan.rptrello.inject.module.DataManagerModule;
import com.retropoktan.rptrello.inject.scope.PerDataManager;

import dagger.Component;

/**
 * Created by RetroPoktan on 12/13/15.
 */
@PerDataManager
@Component(modules = {DataManagerModule.class, ClientApiModule.class})
public interface DataManagerComponent {
    void inject(DataManager dataManager);
}
