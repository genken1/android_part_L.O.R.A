package com.creaters.lora.MainMenu.FacebookOAuth;

import com.creaters.lora.Preferences;
import com.creaters.lora.RetrofitComponent.Controllers.AchievementsController;
import com.creaters.lora.RetrofitComponent.Controllers.UserController;

public class AsyncRequest{
    Preferences preferences;
    public AsyncRequest(Preferences preferences){
        this.preferences = preferences;
    }

    /*создаем новый поток в котором будем отправлять запрос для получения id*/
    Thread emailRequest(UserController userController){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(userController.createGetRequest(preferences, preferences.getValue("email")).isExecuted() && preferences.getValue("id")!=null){
                        break;
                    }else{
                        userController.createGetRequest(preferences, preferences.getValue("email"));
                    }
                }
            }
        });
        return thread;
    }

    /*создаем новый поток в котором будем проверять завершение предыдущего запроса и отправляем новый*/
    Thread idRequest(AchievementsController controllerAch){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(preferences.getValue("id")!=null){
                        controllerAch.createGetRequest(Integer.parseInt(preferences.getValue("id")));
                        break;
                    }
                }
            }
        });
        return thread;
    }
}
