package com.example.rpg_attempt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rpg_attempt.logic.Game;
import com.example.rpg_attempt.mapping.MapHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Game game = new Game(this);
        setContentView(game);
    }
}