package com.example.mad_project;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();

    }
    public  void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN", false);
    }

    public void setEmail(String Email){
        editor.putString("KEY_EMAIL",Email);
        editor.commit();
    }

    public String getEmail(){
        return sharedPreferences.getString("KEY_EMAIL", "");
    }
}
