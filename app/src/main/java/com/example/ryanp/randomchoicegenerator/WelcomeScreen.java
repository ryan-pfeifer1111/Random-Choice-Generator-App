package com.example.ryanp.randomchoicegenerator;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

}
