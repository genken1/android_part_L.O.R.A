package com.creaters.lora;

import retrofit2.Call;

public abstract class SynchronizedRequest extends Thread{
    private volatile Call call;

    public SynchronizedRequest() {

    }

    @Override
    public void run() {
    }
}
