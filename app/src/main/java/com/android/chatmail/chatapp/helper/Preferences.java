package com.android.chatmail.chatapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferences {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String NAME_FILE = "chatapp.prefences";
    private final int MODE = 0;
    private final String KEY_NAME = "name";
    private final String KEY_PHONE = "phone";
    private final String KEY_TOKEN = "token";

    public Preferences(Context contextParm){
        context = contextParm;
        preferences = context.getSharedPreferences(NAME_FILE, MODE);
        editor = preferences.edit();
    }

    public void setUserData(String name, String phone, String token){
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public HashMap<String, String> getUserData(){
        HashMap<String, String> userData = new HashMap<>();

        userData.put(KEY_NAME, preferences.getString(KEY_NAME, null));
        userData.put(KEY_PHONE, preferences.getString(KEY_PHONE, null));
        userData.put(KEY_TOKEN, preferences.getString(KEY_TOKEN, null));

        return userData;
    }
}
