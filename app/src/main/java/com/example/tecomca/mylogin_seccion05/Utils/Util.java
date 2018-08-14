package com.example.tecomca.mylogin_seccion05.Utils;

import android.content.SharedPreferences;

public class Util {

    public static String getUserMailPrefs(SharedPreferences prefecences) {
        return prefecences.getString("email", "");
    }

    public static String getUserPassPrefs(SharedPreferences prefecences) {
        return prefecences.getString("pass", "");
    }

}
