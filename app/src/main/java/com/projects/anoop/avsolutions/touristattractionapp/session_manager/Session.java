package com.projects.anoop.avsolutions.touristattractionapp.session_manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;


    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setValues(String usename) {
        prefs.edit().putString("username", usename).commit();
        prefs.edit().putString("islogin", "true").commit();

    }

    public String getusename() {

        String usename = prefs.getString("username","");
        return usename;
    }

    public boolean isIsLogin()
    {
        String islogin = prefs.getString("islogin","");
        if(islogin != null)
            return  islogin.equals("true");
        return  false;
    }

    public void logOut()
    {
        prefs.edit().putString("islogin", "false").commit();
    }

}
