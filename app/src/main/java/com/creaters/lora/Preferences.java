package com.creaters.lora;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * MODE_WORLD_READABLE deprecated as of API 17
 * !!!This dangerous when storing data!!!
 * */
public class Preferences {

    private SharedPreferences preferences;


    public Preferences(Context context, String FILE_NAME) {
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setValue(String key, String value) {
        getEditor().putString(key,value).commit();
    }

    public void setValue(String key, Integer value) {
        getEditor().putInt(key,value).commit();
    }

    public String getValue(String key) {
        return preferences.getString(key, "");
    }

    public Map<String, ?> getAll(){
        return preferences.getAll();
    }
}
