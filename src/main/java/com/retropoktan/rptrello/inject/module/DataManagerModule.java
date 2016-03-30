package com.retropoktan.rptrello.inject.module;

import android.content.Context;

import com.retropoktan.rptrello.data.db.DBHelper;
import com.retropoktan.rptrello.inject.scope.PerDataManager;

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
    @PerDataManager
    DBHelper provideDBHelper() {
        return new DBHelper();
    }

}
