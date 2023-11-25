package com.example.lab3.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.actions.Action;
import com.example.lab3.actions.EntityAction;
import com.example.lab3.actions.UtilityAction;
import com.example.lab3.logic.Game;

import java.util.List;

public class ActionsUI extends Dialog{

    private Game game;

    public ActionsUI(Context context, Game game) {
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

        List<Action> actions = game.getPlayer().getActions();
        for (Action action : actions) {
            Button button = new Button(getContext());
            button.setText(action.getPrompt() + " " + action.getPositionalPrompt());
            if(action instanceof EntityAction){
                button.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            }else if(action instanceof UtilityAction){
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
