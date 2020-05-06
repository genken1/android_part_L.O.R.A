package com.creaters.lora.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.creaters.lora.MainMenu.MenuActivity;

public class SplashScreenPresenter {
    private SplashScreenModel splashModel;

    public SplashScreenPresenter(Context context){
        splashModel = new SplashScreenModel(context);
    }

    public void startNewActivity(Context context, ImageView image) {
        long delay = 3000;
        Intent intent_menu = new Intent(context, MenuActivity.class);
        splashModel.initAnimation();
        splashModel.animationStart(image);
        splashModel.startNewActivityWithDelay(intent_menu, delay);
    }
}
