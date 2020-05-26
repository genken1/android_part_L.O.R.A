package com.creaters.lora.SplashScreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.creaters.lora.R;
import com.creaters.lora.Settings.AudioPlayer;

public class SplashScreen extends AppCompatActivity {
    private SplashScreenPresenter splash;
    private ImageView image;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        splash = new SplashScreenPresenter(this);
        image = findViewById(R.id.imageView);
        splash.startNewActivity(this, image);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        startService(new Intent(SplashScreen.this, AudioPlayer.class));
    }

}
