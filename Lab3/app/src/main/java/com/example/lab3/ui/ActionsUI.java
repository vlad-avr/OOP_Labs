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

public class ActionsUI extends Dialog{
    public ActionsUI(Context context) {
        super(context);
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

        // Create the list of buttons
        for (int i = 1; i <= 20; i++) {
            Button button = new Button(getContext());
            button.setText("Button " + i);
            buttonLayout.addView(button);
        }

        // Add the button list to the ScrollView
        scrollView.addView(buttonLayout);

        // Add the ScrollView to the main layout
        mainLayout.addView(scrollView);

        // Create the close button
        Button closeButton = new Button(getContext());
        closeButton.setText("Close");
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Close the dialog when the close button is clicked
            }
        });

        // Add the close button to the top-right corner
        LinearLayout.LayoutParams closeButtonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        closeButtonParams.gravity = Gravity.END;
        closeButton.setLayoutParams(closeButtonParams);
        mainLayout.addView(closeButton);

        // Set the content view to the main layout
        setContentView(mainLayout);
    }
}
