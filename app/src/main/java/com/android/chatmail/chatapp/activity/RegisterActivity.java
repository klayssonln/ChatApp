package com.android.chatmail.chatapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.chatmail.chatapp.R;
import com.android.chatmail.chatapp.config.ConfigFirebase;
import com.android.chatmail.chatapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class RegisterActivity extends AppCompatActivity {

    private EditText name, email, password;
    private Button button;
    private User user;
    private DatabaseReference referenceFirebase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        referenceFirebase = ConfigFirebase.getFirebase();

        name = (EditText) findViewById(R.id.newNameId);
        email = (EditText) findViewById(R.id.newEmailId);
        password = (EditText) findViewById(R.id.newPasswordId);
        button = (Button) findViewById(R.id.buttonCreateId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User();
                String newName = name.getText().toString();
                String newEmail = email.getText().toString();
                String newPassword = password.getText().toString();

                if(newName.isEmpty() || newEmail.isEmpty() || newPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Inform all fields.", Toast.LENGTH_SHORT).show();
                } else if(newPassword.length()<6) {
                    Toast.makeText(getApplicationContext(), "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()){
                    Toast.makeText(getApplicationContext(), "The email address is badly formatted.", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.setName(newName);
                    user.setEmail(newEmail);
                    user.setPassword(newPassword);
                    registerUser();
                }
            }
        });
    }

    private void registerUser(){
        firebaseAuth = ConfigFirebase.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
