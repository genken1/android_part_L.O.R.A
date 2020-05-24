package com.creaters.lora.Achievements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.creaters.lora.AchievementsAdapter;
import com.creaters.lora.Preferences;
import com.creaters.lora.R;

import java.util.ArrayList;

public class AchievementsActivity extends AppCompatActivity {
    private Preferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        Context context;
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        preferences = new Preferences(this, "achievements");
        ArrayList<String> list_names = new ArrayList<>(preferences.getAll().keySet());

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);

        rv.setLayoutManager(manager);

        AchievementsAdapter adapter = new AchievementsAdapter(list_names, this);

        rv.setAdapter(adapter);


    }
}
