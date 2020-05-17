package com.creaters.lora.Levels;

import android.content.Context;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Controllers.AchievementsController;

public class LevelPresenter {
    private AchievementsController achievController;
    private Preferences userData;
    private Preferences achievementsData;

    public LevelPresenter(Context context){
        achievController = new AchievementsController();
        userData = new Preferences(context, "user_data");
        achievementsData = new Preferences(context, "achievements");
    }

    public void loadAchievements(){
        achievController.createGetRequest(Integer.parseInt(userData.getValue("id")), achievementsData);
        return;
    }
}
