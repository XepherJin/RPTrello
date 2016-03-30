package com.retropoktan.rptrello.inject.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.retropoktan.rptrello.inject.scope.PerDataManager;
import com.retropoktan.rptrello.protocol.ClientApi;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by RetroPoktan on 12/12/15.
 */
@Module
public class ClientApiModule {
    private static final int API_VERSION = 1;
    private static final String BASE_URL = "https://api.trello.com/" + API_VERSION + "/";

    @Provides
    @PerDataManager
    ClientApi provideClientApi(OkHttpClient client, Converter.Factory converter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converter)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ClientApi.class);
    }

    @Provides
    @PerDataManager
    Converter.Factory provideGsonConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @PerDataManager
    Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Provides
    @PerDataManager
    OkHttpClient provideClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(httpLoggingInterceptor);
        return client;
    }

    @Provides
    @PerDataManager
    HttpLoggingInterceptor provideHttpLog() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }
}
