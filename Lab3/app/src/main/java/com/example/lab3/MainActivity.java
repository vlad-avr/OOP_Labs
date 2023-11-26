package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.lab3.logic.Game;
import com.example.lab3.ui.ActionsPanel;
import com.example.lab3.ui.ControlsPanel;
import com.example.lab3.ui.EnchantPanel;
import com.example.lab3.ui.ShopPanel;

public class MainActivity extends AppCompatActivity {

    private FrameLayout game;
    private Game gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initGame();
        setContentView(R.layout.activity_main);
    }

    private void initGame(){
        game = new FrameLayout(this);
        gameView = new Game (this);
        ControlsPanel controlsPanel = new ControlsPanel(this, gameView);
        ActionsPanel actionsPanel = new ActionsPanel(this, gameView);
        LinearLayout layout = new LinearLayout(this);
        layout.setGravity(Gravity.CENTER | Gravity.TOP);
        ShopPanel shopPanel = new ShopPanel(this, gameView);
        EnchantPanel enchantPanel = new EnchantPanel(this,gameView);
        layout.addView(shopPanel);
        layout.addView(enchantPanel);
        game.addView(gameView);
        game.addView(controlsPanel);
        game.addView(actionsPanel);
        game.addView(layout);

    }
    public void startGame(View view){
        gameView.initGame();
        setContentView(game);
    }

    public void exit(View view){
        finish();
        System.exit(0);
    }
}