package com.retropoktan.rptrello.inject.component;

import com.retropoktan.rptrello.data.db.DBHelper;
import com.retropoktan.rptrello.inject.module.DataManagerModule;
import com.retropoktan.rptrello.model.entity.User;

import dagger.Component;

/**
 * Created by RetroPoktan on 12/13/15.
 */
@Component(modules = DataManagerModule.class)
public interface DataManagerComponent {
    User user();

    DBHelper dbHelper();
}
