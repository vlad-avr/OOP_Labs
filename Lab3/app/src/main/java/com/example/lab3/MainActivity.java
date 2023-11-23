package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lab3.logic.Game;
import com.example.lab3.ui.WidgetsPanel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FrameLayout game = new FrameLayout(this);
        Game gameView = new Game (this);
        WidgetsPanel gameWidgets = new WidgetsPanel (this, gameView.getPlayer());

        game.addView(gameView);
        game.addView(gameWidgets);

        setContentView(game);
    }
}