package com.creaters.lora.MainMenu;

import android.content.Context;
import android.content.Intent;

import com.creaters.lora.Achievements.AchievementsActivity;
import com.creaters.lora.Levels.LevelsActivity;
import com.creaters.lora.Levels.LevelsModel;
import com.creaters.lora.MainMenu.FacebookOAuth.OAuthFB;
import com.creaters.lora.Settings.SettingsActivity;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class MenuPresenter {
    private Context context;
    private Intent intent_settings;
    private Intent intent_achievements;
    private Intent intent_play;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    long delay = 6900;

    private LevelsModel model;
    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    public LoginButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(LoginButton loginButton) {
        this.loginButton = loginButton;
    }

    public MenuPresenter(Context context){
        this.context = context;
    }

    public void startSettingsActivity() {
        intent_settings = new Intent(context, SettingsActivity.class);
        context.startActivity(intent_settings);
    }
    public void startAchievementsActivity() {
        model = new LevelsModel(context);
        model.loadAchievements();
        intent_achievements = new Intent(context, AchievementsActivity.class);
        context.startActivity(intent_achievements);
    }
    public void startPlayActivity() {
        intent_play = new Intent(context, LevelsActivity.class);
        context.startActivity(intent_play);
    }


}
