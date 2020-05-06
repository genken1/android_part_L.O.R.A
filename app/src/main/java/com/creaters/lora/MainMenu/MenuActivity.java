package com.creaters.lora.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.creaters.lora.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button achievements;
    private Button settings;
    private ImageView start;
    private MenuPresenter presenter;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_menu);
        initView();
    }

    public void initView(){
        achievements = findViewById(R.id.achievements);
        settings = findViewById(R.id.settings);
        start = findViewById(R.id.start);

        achievements.setOnClickListener(this);
        settings.setOnClickListener(this);
        start.setOnClickListener(this);

        presenter= new MenuPresenter(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.achievements:
                Toast.makeText(getApplicationContext(), "Achievements Button!", Toast.LENGTH_SHORT).show();
                presenter.startAchievementsActivity();
                break;
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "Settings Button", Toast.LENGTH_SHORT).show();
                presenter.startSettingsFragment();
                break;
            case R.id.start:
                Toast.makeText(getApplicationContext(), "Play Button!", Toast.LENGTH_SHORT).show();
                presenter.startPlayActivity();
                break;
        }
    }

}
