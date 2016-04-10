package com.retropoktan.rptrello.protocol;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by RetroPoktan on 12/13/15.
 */
public interface ClientApi {

    @GET("get_validate_code")
    Observable<String> getCode(@Query("email") String email);
}
