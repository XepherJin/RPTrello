package com.retropoktan.rptrello.data;

import android.content.Context;

import com.google.gson.Gson;
import com.retropoktan.rptrello.data.db.DBHelper;
import com.retropoktan.rptrello.inject.component.DaggerClientApiComponent;
import com.retropoktan.rptrello.inject.component.DaggerDataManagerComponent;
import com.retropoktan.rptrello.inject.component.DataManagerComponent;
import com.retropoktan.rptrello.inject.module.ClientApiModule;
import com.retropoktan.rptrello.inject.module.DataManagerModule;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.protocol.ClientApi;
import com.retropoktan.rptrello.protocol.TokenInterceptor;
import com.squareup.picasso.Picasso;

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
    TokenInterceptor mInterceptor;
    @Inject
    Gson mGson;
    @Inject
    User mUser;
    @Inject
    Picasso mPicasso;

    public DataManager(Context context) {
        initInjection(context);
    }

    private void initInjection(Context context) {
        DataManagerComponent dataManagerComponent = DaggerDataManagerComponent.builder()
                .dataManagerModule(new DataManagerModule(context))
                .build();
        DaggerClientApiComponent.builder()
                .dataManagerComponent(dataManagerComponent)
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

    public void setToken(String token) {
        mInterceptor.setToken(token);
    }

    public Gson getGson() {
        return mGson;
    }

    public User getUser() {
        return mUser;
    }

    public Picasso getPicasso() {
        return mPicasso;
    }

}
