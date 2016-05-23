package com.retropoktan.rptrello.protocol;

import com.retropoktan.rptrello.model.entity.Board;
import com.retropoktan.rptrello.model.entity.Card;
import com.retropoktan.rptrello.model.entity.Member;
import com.retropoktan.rptrello.model.entity.Msg;
import com.retropoktan.rptrello.model.entity.Task;
import com.retropoktan.rptrello.model.entity.Team;
import com.retropoktan.rptrello.model.entity.User;
import com.retropoktan.rptrello.model.req.BoardCreateReq;
import com.retropoktan.rptrello.model.req.BoardUpdateReq;
import com.retropoktan.rptrello.model.req.UserCreateReq;
import com.retropoktan.rptrello.model.req.UserLoginReq;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("project")
    Observable<Msg<Board>> createBoard(@Body BoardCreateReq req);

    @PUT("project/{id}")
    Observable<Msg<Board>> updateBoard(@Body BoardUpdateReq req);

    @GET("project/{id}")
    Observable<Msg<Board>> getBoardDetail(@Path("id") long boardId);

    @DELETE("project/{id}")
    Observable<Msg> deleteBoard();

    ////////////// team //////////////
    @GET("team")
    Observable<Msg<List<Team>>> getTeams();

    ////////////// member ///////////
    @GET("project/{id}/member")
    Observable<Msg<List<Member>>> getBoardMembers(@Path("id") long boardId);

    //////////////// task ///////////////////
    @GET("mission")
    Observable<Msg<List<Task>>> getTasks();

    @GET("mission/{id}")
    Observable<Msg<Task>> getTaskDetail(@Path("id") long taskId);

    @DELETE("mission/{id}")
    Observable<Msg> deleteTask(@Path("id") long taskId);

    //////////////// card ///////////////////
    @GET("mission_list")
    Observable<Msg<List<Card>>> getBoardCards(@Query("belong_project") long boardId);

    @DELETE("mission_list/{id}")
    Observable<Msg> deleteCard(@Path("id") long cardId);
}
