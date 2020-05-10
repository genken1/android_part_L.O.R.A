package com.creaters.lora.RetrofitComponent.Services;

import com.creaters.lora.RetrofitComponent.Entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/*
 * Request from our server!!! It's important. Another request don't work
 * */
public interface AchievementsService {
    @GET("userachievements/getuserachievements")
    Call<User> getUserAchievements(@Query("id") Integer id);
}
