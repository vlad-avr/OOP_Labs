package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lab3.logic.Game;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new Game(this));
    }
}