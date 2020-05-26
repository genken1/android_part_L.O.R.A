package com.creaters.lora.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
/*<<<<<<< Updated upstream*/

import com.creaters.lora.MainMenu.FacebookOAuth.OAuthFB;
/*=======
>>>>>>> Stashed changes*/
import com.creaters.lora.R;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Arrays;

import java.io.IOException;
import java.io.InputStream;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView  achievements;
    private ImageView  settings;
    private ImageView start;
    private ImageButton fbGhost;
    private MenuPresenter presenter;
    private CallbackManager callbackManager;
    private OAuthFB userOAuth;
    private MediaPlayer but_set_sound, music_aud;
    boolean flag = true;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_menu);
        initComponent();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("WrongViewCast")
    public void initComponent() {
        achievements = (ImageView) findViewById(R.id.achievements);
        settings = (ImageView) findViewById(R.id.settings);
        start = (ImageView) findViewById(R.id.start);
        fbGhost = (ImageButton)findViewById(R.id.fb);
        but_set_sound = MediaPlayer.create(this, R.raw.tap_button);
        music_aud = MediaPlayer.create(this, R.raw.audio_music);

        achievements.setOnClickListener(this);
        settings.setOnClickListener(this);
        start.setOnClickListener(this);
        fbGhost.setOnClickListener(this);

        presenter = new MenuPresenter(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.achievements:
                soundPlay(but_set_sound);
                Toast.makeText(getApplicationContext(), "Achievements Button!", Toast.LENGTH_SHORT).show();
                presenter.startAchievementsActivity();
                break;
            case R.id.settings:
                soundPlay(but_set_sound);
                Toast.makeText(getApplicationContext(), "Settings Button", Toast.LENGTH_SHORT).show();
                presenter.startSettingsActivity();
                break;
            case R.id.start:
                if (flag){
                    start.setImageResource(R.drawable.play_button_oopen1);
                    flag = false;
                }else{
                    // возвращаем первую картинку
                    start.setImageResource(R.drawable.play_button_tap1);
                    flag = true;
                }

                soundPlay(but_set_sound);
                Toast.makeText(getApplicationContext(), "Play Button!", Toast.LENGTH_SHORT).show();
                presenter.startPlayActivity();
                break;
            case R.id.fb:
                soundPlay(but_set_sound);
                callbackManager = CallbackManager.Factory.create();
                Toast.makeText(getApplicationContext(), "Facebook Button!", Toast.LENGTH_SHORT).show();
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
                userOAuth = new OAuthFB(this, loginManager, callbackManager);
        }
    }

    public void soundPlay(MediaPlayer sound){
        sound.start();
    }

}
