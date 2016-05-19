package com.retropoktan.rptrello.inject.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.retropoktan.rptrello.inject.scope.PerDataManager;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.protocol.ClientApi;
import com.retropoktan.rptrello.protocol.TokenInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RetroPoktan on 12/12/15.
 */
@Module
public class ClientApiModule {

    private static final int API_VERSION = 1;
    private static final String API = "v" + API_VERSION;
    private static final String BASE_URL = "http://203.88.161.15:7000/" + API + "/";

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
    OkHttpClient provideClient(HttpLoggingInterceptor httpLoggingInterceptor, TokenInterceptor tokenInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(httpLoggingInterceptor);
        builder.interceptors().add(tokenInterceptor);
        return builder.build();
    }

    @Provides
    @PerDataManager
    TokenInterceptor provideTokenInterceptor(User user) {
        String token = user.getToken();
        return new TokenInterceptor(token);
    }

    @Provides
    @PerDataManager
    HttpLoggingInterceptor provideHttpLog() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
