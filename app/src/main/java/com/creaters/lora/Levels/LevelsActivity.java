package com.creaters.lora.Levels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.creaters.lora.R;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LevelsActivity extends AppCompatActivity {
    private Button level;
    private LevelPresenter presenter;
    private LevelsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        level = (Button)findViewById(R.id.level);
        presenter = new LevelPresenter(this);
        model = new LevelsModel(this);

        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.loadAchievements();
            }
        });
    }
}
