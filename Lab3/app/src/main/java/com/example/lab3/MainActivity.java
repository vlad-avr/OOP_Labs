package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.lab3.logic.Game;
import com.example.lab3.ui.ActionsPanel;
import com.example.lab3.ui.ControlsPanel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FrameLayout game = new FrameLayout(this);
        Game gameView = new Game (this);
        ControlsPanel controlsPanel = new ControlsPanel(this, gameView.getPlayer());
        ActionsPanel actionsPanel = new ActionsPanel(this, gameView);

        game.addView(gameView);
        game.addView(controlsPanel);
        game.addView(actionsPanel);

        setContentView(game);
    }
}