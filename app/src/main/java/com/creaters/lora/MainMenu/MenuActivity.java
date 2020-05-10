package com.creaters.lora.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.facebook.login.widget.LoginButton;

import java.io.IOException;
import java.io.InputStream;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button achievements;
    private Button settings;
    private ImageView start;
    private LoginButton loginFacebookButton;
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

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        loginFacebookButton = (LoginButton) findViewById(R.id.login_button);

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
                loginFacebookButton.performClick();
                userOAuth = new OAuthFB(this,  loginFacebookButton, callbackManager);
        }
    }

}
