package com.example.rpg_attempt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rpg_attempt.mapping.MapHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapHolder mapHolder = new MapHolder();
        mapHolder.generateMapPlan();
        setContentView(R.layout.activity_main);
    }
}