package com.creaters.lora.MainMenu;

import android.content.Context;
import android.content.Intent;

import com.creaters.lora.Achievements.AchievementsFragment;
import com.creaters.lora.Levels.LevelsActivity;
import com.creaters.lora.Settings.SettingsFragment;

public class MenuPresenter {
    private Context context;
    private Intent intent_settings;
    private Intent intent_achievements;
    private Intent intent_play;

    public MenuPresenter(Context context){
        this.context = context;
    }

    public void startSettingsFragment() {
        intent_settings = new Intent(context, SettingsFragment.class);
        context.startActivity(intent_settings);
    }
    public void startAchievementsActivity() {
        intent_achievements = new Intent(context, AchievementsFragment.class);
        context.startActivity(intent_achievements);
    }
    public void startPlayActivity() {
        intent_play = new Intent(context, LevelsActivity.class);
        context.startActivity(intent_play);
    }

}
