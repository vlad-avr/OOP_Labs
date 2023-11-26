package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lab3.logic.Game;

public class DeathActivity extends AppCompatActivity {

    private FrameLayout gameLayout;
    private Game game;
    public DeathActivity(FrameLayout gameLayout, Game game){
        super();
        this.gameLayout = gameLayout;
        this.game = game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);
    }

    public void restartGame(View view){
        game.initGame();
        setContentView(gameLayout);
    }

    public void exit(View view){
        finish();
        System.exit(0);
    }

}