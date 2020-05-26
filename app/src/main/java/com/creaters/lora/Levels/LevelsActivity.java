package com.creaters.lora.Levels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.creaters.lora.R;
import com.unity3d.player.UnityPlayerActivity;

public class LevelsActivity extends AppCompatActivity {
    private ImageView level;
    private LevelPresenter presenter;
    private LevelsModel model;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        level = (ImageView) findViewById(R.id.level);
        presenter = new LevelPresenter(this);
        model = new LevelsModel(this);
        intent = new Intent(this, OwerrideUnityPlayer.class);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Start level", Toast.LENGTH_SHORT).show();
                //model.loadAchievements();
            }

        });
    }
}
