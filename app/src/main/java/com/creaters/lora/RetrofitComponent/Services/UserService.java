package com.creaters.lora.RetrofitComponent.Services;

import com.creaters.lora.RetrofitComponent.Entities.User;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
/*
* Request from our server!!! It's important. Another request don't work
* */
public interface UserService {
    @GET("users/getuserbyemail")
    Call<User> getUserByEmail(@Query("email") String email);

    @GET("users/getuser")
    Call<User> getUserById(@Query("id") Integer id);

    /*@Headers("Content-type: text/plain")
    @FormUrlEncoded
    @POST("users/create")
    Call<User> create(@Field("name") String name,
                      @Field("last_name") String last_name,
                      @Field("email") String email);*/

    @Headers("Content-type: text/plain")
    @POST("users/create")
    Call<String> create(@Body String param);
}
