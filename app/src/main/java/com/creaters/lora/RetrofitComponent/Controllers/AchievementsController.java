package com.creaters.lora.RetrofitComponent.Controllers;

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

    public void createGetRequest(Integer id) {
        Call<List<Achievements>> call = service.getUserAchievements(id);
        call.enqueue(new Callback<List<Achievements>>() {
            @Override
            public void onResponse(Call<List<Achievements>> call, Response<List<Achievements>> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response.code());
                    return;
                }
                for (int i = 0; i < response.body().size(); i++) {
                    Achievements achievements = new Achievements(response.body().get(i).getId(), response.body().get(i).getName(), response.body().get(i).getUrl());;
                }
                //preferences.setValue("id", id);
            }

            @Override
            public void onFailure(Call<List<Achievements>> call, Throwable t) {
                System.out.println("fail");
                //error handling
            }
        });
    }
}
