package com.creaters.lora.Levels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.creaters.lora.R;

public class LevelsActivity extends AppCompatActivity {
    private Button level;
    private LevelPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        level = (Button)findViewById(R.id.level);
        presenter = new LevelPresenter(this);
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadAchievements();
            }
        });
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadAchievements();
            }
        });
    }
}
