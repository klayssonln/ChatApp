package com.android.chatmail.chatapp.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.chatmail.chatapp.R;


public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText email, password;
    private TextView textCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button) findViewById(R.id.buttonLoginId);
        email = (EditText) findViewById(R.id.editEmailId);
        password = (EditText) findViewById(R.id.editPasswordId);
        textCreateAccount = (TextView) findViewById(R.id.textCreateId);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


    }



}
