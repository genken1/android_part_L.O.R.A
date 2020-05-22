package com.creaters.lora.Levels;


import android.content.Context;

import com.creaters.lora.ImageComponent;
import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Controllers.AchievementsController;
import com.creaters.lora.SynchronizedRequest;

import java.util.Iterator;
import java.util.Set;

import retrofit2.Call;

/**
 * This class create new Thread and
 * download image into storage.
 * After get request we save data.
 */
public class LevelSyncRequest extends SynchronizedRequest {
    private Context context;
    private ImageComponent downloader;
    private Preferences userData;
    private Preferences achievementsData;
    private AchievementsController achievController;
    private volatile Call call;
    private final String path = "/ImageAchievements";


    public LevelSyncRequest(Context context, Preferences userData, Preferences achievementsData, AchievementsController achievController) {
        this.context = context;
        this.userData = userData;
        this.achievementsData = achievementsData;
        this.achievController = achievController;
        downloader = new ImageComponent(context, path);
    }

    @Override
    public void run() {
        call = achievController.createGetRequest(Integer.parseInt(userData.getValue("id")), achievementsData);

        while (true) {
            if (call.isExecuted()) {
                if (!achievementsData.getAll().isEmpty()) {
                    Set<String> names = achievementsData.getAll().keySet();
                    Iterator<String> iterator = names.iterator();
                    while (iterator.hasNext()) {
                        String name = (String) iterator.next();
                        downloader.saveImage(name, achievementsData.getValue(name));
                    }
                    break;
                }
                break;
            }
        }

    }
}
