package com.creaters.lora;

import com.creaters.lora.RetrofitComponent.Controllers.UserController;
import com.creaters.lora.RetrofitComponent.Entities.User;

import retrofit2.Call;

public abstract class SynchronizedRequest extends Thread{
    private volatile Call call;

    public SynchronizedRequest() {

    }

    @Override
    public void run() {
    }
}
