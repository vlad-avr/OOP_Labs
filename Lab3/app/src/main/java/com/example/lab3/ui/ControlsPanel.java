package com.example.lab3.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lab3.entities.Player;
import com.example.lab3.logic.Game;

public class ControlsPanel extends LinearLayout {
    private Button bUp;
    private Button bDown;
    private Button bRight;
    private Button bLeft;

    private Game game;

    public ControlsPanel(Context context, Game game) {
        super(context);
        this.game = game;
        this.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        bDown = new Button(context);
        bDown.setWidth(64);
        bDown.setText("Down");
        bDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getPlayer().move(1, 0);
            }
        });
        bLeft = new Button(context);
        bLeft.setWidth(64);
        bLeft.setText("Left");
        bLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getPlayer().move(0, -1);
            }
        });
        bRight = new Button(context);
        bRight.setWidth(64);
        bRight.setText("Right");
        bRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getPlayer().move(0, 1);
            }
        });
        bUp = new Button(context);
        bUp.setWidth(64);
        bUp.setText("Up");
        bUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getPlayer().move(-1, 0);
            }
        });
        addView(bLeft);
        addView(bDown);
        addView(bUp);
        addView(bRight);

    }
}
