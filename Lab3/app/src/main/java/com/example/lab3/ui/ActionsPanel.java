package com.example.lab3.ui;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lab3.logic.Game;

public class ActionsPanel extends LinearLayout {

    private Game game;
    private Button actionButton;
    private Button inventoryButton;
    public ActionsPanel(Context context, Game game) {
        super(context);
        this.game = game;
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(android.view.Gravity.CENTER_VERTICAL | android.view.Gravity.END);
        actionButton = new Button(context);
        actionButton.setText("Act.");
        actionButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        actionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionsUI actionsUI = new ActionsUI(context, game);
                actionsUI.show();
            }
        });
        inventoryButton = new Button(context);
        inventoryButton.setText("Inv.");
        inventoryButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        inventoryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InventoryUI inventoryUI = new InventoryUI(context, game);
                inventoryUI.show();
            }
        });
        addView(actionButton);
        addView(inventoryButton);
    }

}
