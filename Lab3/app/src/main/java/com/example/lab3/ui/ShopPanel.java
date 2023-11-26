package com.example.lab3.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lab3.logic.Game;

public class ShopPanel extends LinearLayout {

    private Button shopButton;
    private Game game;
    public ShopPanel(Context context, Game game) {
        super(context);
        this.game = game;
        this.setGravity(Gravity.CENTER | Gravity.TOP);
        shopButton = new Button(context);
        shopButton.setWidth(64);
        shopButton.setText("Shop");
        shopButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopUI shopUI = new ShopUI(context, game);
                shopUI.show();
            }
        });
        this.addView(shopButton);
    }
}
