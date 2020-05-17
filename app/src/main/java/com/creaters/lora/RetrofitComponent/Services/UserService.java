package com.creaters.lora.RetrofitComponent.Services;

import com.creaters.lora.RetrofitComponent.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
/*
*  Request from our server!
*  Another request don't work. It's important.
*  For example:
*  "users/getuserbyemail" != "users/emailuser" or another
* */
public interface UserService {
    @GET("users/getuserbyemail")
    Call<User> getUserByEmail(@Query("email") String email);

    @GET("users/getuser")
    Call<User> getUserById(@Query("id") Integer id);

    @Headers("Content-type: text/plain")
    @POST("users/create")
    Call<String> create(@Body String param);
}
