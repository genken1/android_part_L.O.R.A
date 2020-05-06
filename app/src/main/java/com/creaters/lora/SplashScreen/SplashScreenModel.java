package com.creaters.lora.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.creaters.lora.R;

public class SplashScreenModel {
    private Context context;
    private Animation anim;

    public SplashScreenModel(Context context){ this.context=context; }

    public void initAnimation() {
        anim = AnimationUtils.loadAnimation(context, R.anim.attenuation);
    }

    public void animationStart(ImageView image){
        image.startAnimation(anim);
    }

    /*Intent new class of activity, delay in milliseconds*/
    public void startNewActivityWithDelay(final Intent activity, final long milli) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(milli);
                } catch (
                        InterruptedException e) {
                    Log.e("Thread error", "MainActivity thread sleep ERROR");
                    e.printStackTrace();
                }finally {
                    context.startActivity(activity);
                }
            }
        };
        thread.start();
    }
}
