package com.creaters.lora.RetrofitComponent.Controllers;

import android.util.Log;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Entities.Achievements;
import com.creaters.lora.RetrofitComponent.Services.AchievementsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AchievementsController {
    public static final String BASE_URL = "https://serverlora.herokuapp.com/";
    private AchievementsService service;
    private Retrofit retrofit;

    public AchievementsController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(AchievementsService.class);
    }

    public Call createGetRequest(Integer id, Preferences achievementsData) {
        Call<List<Achievements>> call = service.getUserAchievements(id);
        call.enqueue(new Callback<List<Achievements>>() {
            @Override
            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
                Achievements achievements;
                if (!response.isSuccessful()) {
                    Log.e("AchievementsController.createGetRequest", String.valueOf(response.code()));
                    return;
                }
                for (int i = 0; i < response.body().size(); i++) {
                    achievements = new Achievements(response.body().get(i).getId(), response.body().get(i).getName(), response.body().get(i).getUrl());
                    achievementsData.setValue(response.body().get(i).getName(), response.body().get(i).getUrl());
                    Log.i("ACHIEVEMENTS",achievements.getId()+" "+achievements.getName()+" "+achievements.getUrl());
                }
            }

            @Override
            public void onFailure(Call<List<Achievements>> call, Throwable t) {
                Log.e("AchievementsController.onFailure", "fail");
            }
        });
        return call;
    }
}
