package com.retropoktan.rptrello.data;

import android.content.Context;

import com.google.gson.Gson;
import com.retropoktan.rptrello.data.db.DBHelper;
import com.retropoktan.rptrello.inject.component.DaggerDataManagerComponent;
import com.retropoktan.rptrello.inject.module.ClientApiModule;
import com.retropoktan.rptrello.inject.module.DataManagerModule;
import com.retropoktan.rptrello.protocol.ClientApi;

import javax.inject.Inject;

/**
 * Created by RetroPoktan on 12/12/15.
 */
public class DataManager {

    @Inject
    DBHelper mDBHelper;
    @Inject
    ClientApi mClientApi;
    @Inject
    Gson mGson;

    public DataManager(Context context) {
        initInjection(context);
    }

    private void initInjection(Context context) {
        DaggerDataManagerComponent.builder()
                .dataManagerModule(new DataManagerModule(context))
                .clientApiModule(new ClientApiModule())
                .build()
                .inject(this);
    }

    public DBHelper getDBHelper() {
        return mDBHelper;
    }

    public ClientApi getClientApi() {
        return mClientApi;
    }

    public Gson getGson() {
        return mGson;
    }

}
