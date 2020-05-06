package com.creaters.lora;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users/getusers")
    Call<List<User>> all();

    @GET("users/{isbn}")
    Call<User> get(@Path("isbn") String isbn);

    @POST("users/new")
    Call<User> create(@Body User user);
}
