package com.example.lab3.ui;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lab3.entities.Player;

public class WidgetsPanel extends LinearLayout {
    private Button bUp;
    private Button bDown;
    private Button bRight;
    private Button bLeft;

    private Player playerRef;

    public WidgetsPanel(Context context, Player player) {
        super(context);
        this.playerRef = player;
        bDown = new Button(context);
        bDown.setWidth(64);
        bDown.setText("Down");
        bDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playerRef.move(0, -1);
            }
        });
        bLeft = new Button(context);
        bLeft.setWidth(64);
        bLeft.setText("Left");
        bLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playerRef.move(-1, 0);
            }
        });
        bRight = new Button(context);
        bRight.setWidth(64);
        bRight.setText("Right");
        bRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playerRef.move(1, 0);
            }
        });
        bUp = new Button(context);
        bUp.setWidth(64);
        bUp.setText("Up");
        bUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playerRef.move(0, 1);
            }
        });
        addView(bDown);
        addView(bLeft);
        addView(bRight);
        addView(bUp);

    }
}
