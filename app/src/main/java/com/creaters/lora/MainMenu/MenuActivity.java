package com.creaters.lora.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.creaters.lora.MainMenu.FacebookOAuth.OAuthFB;
import com.creaters.lora.R;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Arrays;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button achievements;
    private Button settings;
    private ImageView start;
    private ImageButton fbGhost;
    private MenuPresenter presenter;
    private CallbackManager callbackManager;
    private OAuthFB userOAuth;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_menu);
        initComponent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initComponent() {
        achievements = (Button) findViewById(R.id.achievements);
        settings = (Button) findViewById(R.id.settings);
        start = (ImageView) findViewById(R.id.start);
        fbGhost = (ImageButton)findViewById(R.id.fb);

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
                Toast.makeText(getApplicationContext(), "Achievements Button!", Toast.LENGTH_SHORT).show();
                presenter.startAchievementsActivity();
                break;
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "Settings Button", Toast.LENGTH_SHORT).show();
                presenter.startSettingsActivity();
                break;
            case R.id.start:
                Toast.makeText(getApplicationContext(), "Play Button!", Toast.LENGTH_SHORT).show();
                presenter.startPlayActivity();
                break;
            case R.id.fb:
                callbackManager = CallbackManager.Factory.create();
                Toast.makeText(getApplicationContext(), "Facebook Button!", Toast.LENGTH_SHORT).show();
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
                userOAuth = new OAuthFB(this, loginManager, callbackManager);
        }
    }

}
