package com.example.tecomca.mylogin_seccion05.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tecomca.mylogin_seccion05.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPassField;

    private Button mRegisterBtn;
    private Button mBackBtn;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mEmailField = (EditText) findViewById(R.id.editTextEmail);
        mPassField = (EditText) findViewById(R.id.editTextPass);

        mRegisterBtn = (Button) findViewById(R.id.btnRegister);
//        mBackBtn = (Button) findViewById(R.id.btnBack);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null ){
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }
        };

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSingUp();
            }
        });

//        mBackBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });
    }

    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSingUp(){
        String email = mEmailField.getText().toString();
        String password = mPassField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "Campos estan vacios", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Problemas al registrar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
