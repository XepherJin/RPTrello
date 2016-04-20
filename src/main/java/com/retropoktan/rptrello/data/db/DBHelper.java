package com.retropoktan.rptrello.data.db;

import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirDeleteCallback;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;

import java.lang.reflect.Type;

import rx.Observable;

/**
 * Created by RetroPoktan on 12/13/15.
 */
public class DBHelper {

    private static final String TAG = DBHelper.class.getSimpleName();

    public void put(String key, Object object) {
        if (object == null) return;
        Reservoir.putAsync(key, object, new ReservoirPutCallback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "Put success!");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public boolean contains(String key) {
        try {
            return Reservoir.contains(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void delete(String key) {
        if (this.contains(key))
            Reservoir.deleteAsync(key);
    }

    public void refresh(final String key, final Object object) {
        if (contains(key)) {
            Reservoir.deleteAsync(key, new ReservoirDeleteCallback() {
                @Override
                public void onSuccess() {
                    put(key, object);
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            put(key, object);
        }
    }

    public <T> Observable<T> getAsync(String key, Class<T> clazz) {
        return Reservoir.getAsync(key, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        try {
            return Reservoir.get(key, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> Observable<T> get(Class<T> clazz) {
        String key = clazz.getSimpleName();
        return getAsync(key, clazz);
    }

    public <T> void getAsync(String key, Type typeOfT, ReservoirGetCallback<T> callback) {
        Reservoir.getAsync(key, typeOfT, callback);
    }
}
