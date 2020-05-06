package com.creaters.lora;

import android.content.Context;
import android.content.SharedPreferences;

/*Created 06.05.2020*/
public class Preferences {

    final static String FILE_NAME = "preferences";

    final static String PREF_PIN = "pin";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setPin(String data) {
        getEditor().putString(PREF_PIN, data).commit();
    }

    public String getPin() {
        return preferences.getString(PREF_PIN, "");
    }
}
