package com.example.tecomca.mylogin_seccion05.Splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.tecomca.mylogin_seccion05.Activities.LoginActivity;
import com.example.tecomca.mylogin_seccion05.Activities.MainActivity;
import com.example.tecomca.mylogin_seccion05.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Preferences", getBaseContext().MODE_PRIVATE);

        Intent intentLogin = new Intent (this, LoginActivity.class);
        Intent intentMain = new Intent (this, MainActivity.class);

        if (!TextUtils.isEmpty(Util.getUserMailPrefs(prefs)) &&
                !TextUtils.isEmpty(Util.getUserPassPrefs(prefs))) {
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }
        finish(); // matamos el activity
    }
}
