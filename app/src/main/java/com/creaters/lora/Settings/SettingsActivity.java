package com.creaters.lora.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.creaters.lora.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Switch sw1 = (Switch) findViewById(R.id.switch1);
        Switch back_music = (Switch) findViewById(R.id.switch3);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    stopService(new Intent(SettingsActivity.this, AudioPlayer.class));
                }else{
                    startService(new Intent(SettingsActivity.this, AudioPlayer.class));
                }
            }
        });
    }
}
