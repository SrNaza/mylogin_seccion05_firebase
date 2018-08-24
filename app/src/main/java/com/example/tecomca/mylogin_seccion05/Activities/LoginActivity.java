package com.example.tecomca.mylogin_seccion05.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tecomca.mylogin_seccion05.R;
import com.example.tecomca.mylogin_seccion05.Sql.DatabaseHelper;
import com.example.tecomca.mylogin_seccion05.Utils.Util;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences prefs;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogin;
    private Button btnRegister;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUI();


        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                //verifyFromSQLite();
                if (login(email, password)) {
                    goToMain();
                    saveOnPreferences(email, password);
                }
            }
        });

        initObjects();
        initListeners();
    }
    private void bindUI() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
    }

    private void setCredentialsIfExist() {
        String email = Util.getUserMailPrefs(prefs);
        String password = Util.getUserPassPrefs(prefs);
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            editTextEmail.setText(email);
            editTextPassword.setText(password);
        }
    }

    private boolean login(String email, String password) {
        if  (!isValidEmail(email)){
            Toast.makeText(this, "Email is not valid, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this, "Password is not valid, 4 characters or more, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void saveOnPreferences(String email, String password) {
        if (switchRemember.isChecked()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.putString("pass", password);
//            editor.commit(); // sincrono
            editor.apply(); // asincrono
        }
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword (String password) {
        return password.length() >= 4;
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(LoginActivity.this);
    }

    private void initListeners(){
        //btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.buttonLogin:
                verifyFromSQLite();
                break;
            case R.id.buttonRegister: // los onclick de los listener como estan arriba
                Intent intentRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (login(email, password)) {
//            goToMain();
            if (databaseHelper.checkUser(email,password)){
                Intent accountIntent = new Intent (LoginActivity.this, MainActivity.class);
                accountIntent.putExtra("EMAIL", email);
                emptyInputEditText();
                startActivity(accountIntent);
            }else{
                Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_LONG).show();
            }
            saveOnPreferences(email, password);
        }
    }

    private void emptyInputEditText(){
        editTextEmail.setText(null);
        editTextPassword.setText(null);
    }
}
