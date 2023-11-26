package com.example.lab3.ui;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.lab3.logic.Game;

public class ShopUI extends Dialog {

    private Game game;
    public ShopUI(@NonNull Context context, Game game) {
        super(context);
        this.game = game;
    }
}
