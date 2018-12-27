package com.android.chatmail.chatapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.chatmail.chatapp.R;
import com.android.chatmail.chatapp.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
