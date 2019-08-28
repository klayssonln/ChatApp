package com.android.chatmail.chatapp.activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.chatmail.chatapp.R;
import com.android.chatmail.chatapp.config.ConfigFirebase;
import com.android.chatmail.chatapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText email, password;
    private User user;
    private TextView textCreateAccount;
    private DatabaseReference referenceFirebase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenceFirebase = ConfigFirebase.getFirebase();

        buttonLogin = (Button) findViewById(R.id.buttonLoginId);
        email = (EditText) findViewById(R.id.editEmailId);
        password = (EditText) findViewById(R.id.editPasswordId);
        textCreateAccount = (TextView) findViewById(R.id.textCreateId);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                String newMail = email.getText().toString();
                String newPassword = password.getText().toString();

                if(newMail.isEmpty()||newPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Inform all fields", Toast.LENGTH_SHORT).show();
                } else {
                    user.setEmail(newMail);
                    user.setPassword(newPassword);
                    loginUser();
                }


            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void loginUser(){
        firebaseAuth = ConfigFirebase.getFirebaseAuth();
        firebaseAuth.signInWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                            Log.e("ERROR LOGIN", task.getException().toString());
                        }
                    }
                });

    }



}
