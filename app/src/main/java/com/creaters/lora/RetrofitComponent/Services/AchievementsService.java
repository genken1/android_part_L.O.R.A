package com.creaters.lora.RetrofitComponent.Services;

import com.creaters.lora.RetrofitComponent.Entities.Achievements;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/*
 *  Request from our server!
 *  Another request don't work. It's important.
 *  For example:
 *  "userachievements/getuserachievements" != "userachievements/userachiv" or another
 * */
public interface AchievementsService {
    @GET("userachievements/getuserachievements")
    Call<List<Achievements>> getUserAchievements(@Query("id") Integer id);
}
