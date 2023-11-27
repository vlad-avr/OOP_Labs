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
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.lab3.MainActivity;
import com.example.lab3.R;
import com.example.lab3.actions.EnchantAction;
import com.example.lab3.inventory.Enchantment;

import java.util.List;

public class DeathScreenUI extends Dialog {

    private String text;
    private MainActivity mainActivity;
    public DeathScreenUI(MainActivity context, String text) {
        super(context);
        this.text = text;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mainActivity = context;
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
        mainLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dead_fungi));

        // Create a LinearLayout for the button list
        LinearLayout buttonLayout = new LinearLayout(getContext());
        buttonLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonLayout.setOrientation(LinearLayout.VERTICAL);

        TextView deathText = new TextView(getContext());
        deathText.setText(text);
        deathText.setTextSize(35);
        deathText.setGravity(Gravity.CENTER);

        Button exitButton = new Button(getContext());
        exitButton.setText("EXIT");
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.exit(v);
            }
        });
        Button restartButton = new Button(getContext());
        restartButton.setText("RESTART");
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startGame(v);
            }
        });
        this.setCancelable(false);
        mainLayout.addView(deathText);
        mainLayout.addView(restartButton);
        mainLayout.addView(exitButton);
        mainLayout.addView(buttonLayout);
        setContentView(mainLayout);
    }
}
