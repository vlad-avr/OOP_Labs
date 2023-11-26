package com.example.lab3.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lab3.logic.Game;

public class EnchantPanel extends LinearLayout {
    private Button shopButton;
    private Game game;
    public EnchantPanel(Context context, Game game) {
        super(context);
        this.game = game;
        this.setGravity(Gravity.CENTER | Gravity.TOP);
        shopButton = new Button(context);
        shopButton.setWidth(256);
        shopButton.setText("Enchant");
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnchantUI enchantUI = new EnchantUI(context, game);
                enchantUI.show();
            }
        });
        this.addView(shopButton);
    }
}
