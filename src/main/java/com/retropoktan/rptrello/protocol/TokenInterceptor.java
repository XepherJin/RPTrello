package com.retropoktan.rptrello.protocol;

import android.text.TextUtils;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by RetroPoktan on 4/21/16.
 */
public class TokenInterceptor implements Interceptor {

    private String mToken;

    public TokenInterceptor() {

    }

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
