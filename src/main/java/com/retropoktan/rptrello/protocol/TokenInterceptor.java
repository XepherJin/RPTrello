package com.retropoktan.rptrello.protocol;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class TokenInterceptor implements Interceptor {

    private String mToken;

    public TokenInterceptor(String token) {
        mToken = token;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        if (!TextUtils.isEmpty(mToken)) {
            builder.addHeader("Private-Token", mToken);
        }
        builder.method(original.method(), original.body());
        Request request = builder.build();
        return chain.proceed(request);
    }
}
