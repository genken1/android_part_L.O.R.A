package com.creaters.lora;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    //final static String FILE_NAME = "user_data";

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
}
