package com.creaters.lora.MainMenu.FacebookOAuth;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Controllers.UserController;
import com.creaters.lora.RetrofitComponent.Entities.User;

import retrofit2.Call;

public class SyncRequest extends Thread {
    private Preferences preferences;
    private UserController userController;
    private User user;
    private volatile Call call;

    public SyncRequest(Preferences preferences, UserController userController, User user) {
        this.preferences = preferences;
        this.userController = userController;
        this.user = user;
    }

    @Override
    public void run() {
        call = userController.createPostRequest(user);

        while (true) {
            if(call.isExecuted()) {
                if (!user.getEmail().isEmpty())
                    userController.createGetRequest(preferences, user.getEmail());
            }
        }
    }
}
