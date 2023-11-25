package com.example.lab3.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.actions.InventoryAction;
import com.example.lab3.inventory.Armor;
import com.example.lab3.inventory.Item;
import com.example.lab3.inventory.Weapon;
import com.example.lab3.logic.Game;

import java.util.List;

public class InventoryUI extends Dialog {
    private Game game;

    public InventoryUI(Context context, Game game) {
        super(context);
        this.game = game;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the main layout for the dialog
        LinearLayout mainLayout = new LinearLayout(getContext());
        mainLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.CENTER);

        // Create a ScrollView to support scrolling
        ScrollView scrollView = new ScrollView(getContext());
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Create a LinearLayout for the button list
        LinearLayout buttonLayout = new LinearLayout(getContext());
        buttonLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonLayout.setOrientation(LinearLayout.VERTICAL);

        List<Item> items = game.getPlayer().getInventory();
        for (Item item : items) {
            InventoryAction action = item.getAction();
            Button button = new Button(getContext());
            button.setText(action.getPrompt() + " " + action.getExtraPrompt());
            if(item instanceof Weapon){
                button.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
                if(item == game.getPlayer().getWeapon()){
                    button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.equipped));
                }
            }else if(item instanceof Armor){
                button.setTextColor(ContextCompat.getColor(getContext(), R.color.armor));
                if(item == game.getPlayer().getArmor()){
                    button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.equipped));
                }
            }else{
                button.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.getPlayer().act(action);
                    dismiss();
                }
            });
            buttonLayout.addView(button);
        }
        scrollView.addView(buttonLayout);
        mainLayout.addView(scrollView);
        setContentView(mainLayout);
    }
}
