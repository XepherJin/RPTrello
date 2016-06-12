package com.retropoktan.rptrello.inject.module;

import android.content.Context;

import com.retropoktan.rptrello.data.db.DBHelper;
import com.retropoktan.rptrello.model.entity.User;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RetroPoktan on 12/13/15.
 */
@Module
public class DataManagerModule {
    private final Context mContext;

    public DataManagerModule(Context context) {
        mContext = context;
    }

    @Provides
    DBHelper provideDBHelper() {
        return new DBHelper();
    }

    @Provides
    User provideUser(DBHelper helper) {
        User user = helper.get(User.TAG, User.class);
        return user == null ? new User() : user;
    }

    @Provides
    Picasso providePicasso() {
        return Picasso.with(mContext);
    }

}
