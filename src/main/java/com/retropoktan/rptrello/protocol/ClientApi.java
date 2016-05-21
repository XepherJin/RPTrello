package com.retropoktan.rptrello.protocol;

import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.model.entity.Team;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.model.req.UserCreateReq;
import com.retropoktan.rptrello.model.req.UserLoginReq;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by RetroPoktan on 12/13/15.
 */
public interface ClientApi {

    /////////////////// user ////////////
    @GET("get_validate_code")
    Observable<Msg> getCode(@Query("email") String email);

    @POST("register")
    Observable<Msg<User>> createAccount(@Body UserCreateReq req);

    @POST("login")
    Observable<Msg<User>> login(@Body UserLoginReq req);

    //////////////// board //////////////
    @GET("project")
    Observable<Msg<List<Board>>> getBoards();

    @GET("project/{id}")
    Observable<Msg<Board>> getBoardDetail(@Path("id") int projectId);

    ////////////// team //////////////
    @GET("team")
    Observable<Msg<List<Team>>> getTeams();

    //////////////// task ///////////////////
    @GET("mission")
    Observable<Msg<List<Task>>> getTasks();
}
