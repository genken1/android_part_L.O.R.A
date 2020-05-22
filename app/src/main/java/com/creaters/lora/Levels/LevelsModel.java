package com.creaters.lora.Levels;

import android.content.Context;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Controllers.AchievementsController;


public class LevelsModel {
    private Context context;
    private AchievementsController achievController;
    private Preferences userData;
    private Preferences achievementsData;
    private LevelSyncRequest syncRequest;

    public LevelsModel(Context context){
        this.context = context;
        achievController = new AchievementsController();
        userData = new Preferences(context, "user_data");
        achievementsData = new Preferences(context, "achievements");
        syncRequest = new LevelSyncRequest(context, userData, achievementsData, achievController);
    }

    public void loadAchievements(){
        /*Async request to our server*/
        if(!userData.getValue("id").isEmpty())
            syncRequest.run();
    }
}
