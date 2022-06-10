package com.ppdm.androidtest.remote;

import com.ppdm.androidtest.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Path;

public interface RouterInterface {
    @POST("/user")
    Call<User> postUser(@Body User user);

    @GET("/user")
    Call<List<User>> getUsers();
}
