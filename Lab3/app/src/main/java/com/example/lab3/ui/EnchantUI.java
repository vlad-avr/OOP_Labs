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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.actions.BuyAction;
import com.example.lab3.actions.EnchantAction;
import com.example.lab3.inventory.Enchantment;
import com.example.lab3.inventory.ShopItem;
import com.example.lab3.logic.Game;

import java.util.List;

public class EnchantUI extends Dialog {

    private Game game;

    public EnchantUI(Context context, Game game) {
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


        ScrollView enchantmentScrollView = new ScrollView(getContext());
        enchantmentScrollView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        // Create a LinearLayout for the button list
        LinearLayout enchantmentLayout = new LinearLayout(getContext());
        enchantmentLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        enchantmentLayout.setOrientation(LinearLayout.VERTICAL);
        List<Enchantment> enchantments = game.getShop().getEnchantments();
        for (Enchantment enchantment : enchantments) {
            EnchantAction action = enchantment.getAction();
            Button button = new Button(getContext());
            button.setText(action.getPrompt() + " " + action.getExtraPrompt());
            button.setTextColor(ContextCompat.getColor(getContext(), R.color.shroom));
            if(game.getPlayer().getGoldCount() < enchantment.getPrice() ||
                    (enchantment.getType() == Enchantment.EnchantmentType.MEND_WEAPON && game.getPlayer().getWeapon().isFixed()) ||
                    (enchantment.getType() == Enchantment.EnchantmentType.MEND_ARMOR && game.getPlayer().getArmor().isFixed()) ||
                    (enchantment.getType() == Enchantment.EnchantmentType.ENCHANT_FOR_DODGE && game.getPlayer().getArmor().maxDodge()) ||
                    (enchantment.getType() == Enchantment.EnchantmentType.ENCHANT_FOR_CRITS && game.getPlayer().getWeapon().maxCrit())){
                button.setEnabled(false);
                button.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.unable));
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.getPlayer().act(action);
                    dismiss();
                }
            });
            enchantmentLayout.addView(button);
        }
        enchantmentScrollView.addView(enchantmentLayout);
        mainLayout.addView(enchantmentScrollView);
        setContentView(mainLayout);
    }
}
