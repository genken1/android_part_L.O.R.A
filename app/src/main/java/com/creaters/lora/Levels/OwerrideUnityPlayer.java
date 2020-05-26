package com.creaters.lora.Levels;


import com.unity3d.player.UnityPlayerActivity;

public class OwerrideUnityPlayer extends UnityPlayerActivity {
    private LevelsModel model;

    @Override
    public void onUnityPlayerUnloaded() {
        model = new LevelsModel(this);
        model.loadAchievements();
        super.onUnityPlayerUnloaded();
    }

    @Override
    public void onUnityPlayerQuitted() {
        super.onUnityPlayerQuitted();
        model = new LevelsModel(this);
        model.loadAchievements();
    }
}
