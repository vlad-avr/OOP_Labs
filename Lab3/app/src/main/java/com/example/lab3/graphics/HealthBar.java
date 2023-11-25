package com.example.lab3.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.entities.Entity;
import com.example.lab3.mapping.MapHolder;

public class HealthBar {

    private Entity entity;
    private Paint borderPaint, healthPaint;
    private int width, height, margin;

    public HealthBar(Context context, Entity entity) {
        this.entity = entity;
        width = 64;
        height = 10;
        margin = 2;

        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        //int healthColor = ContextCompat.getColor(context, R.color.healthBarContent);
        //healthPaint.setColor(healthColor);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        float x = (float) entity.getPositionX();
        float y = (float) entity.getPositionY();
        float distToEntity = 30;
        float healthPointPercentage = (float) entity.getHealth()/(float) entity.getMaxHealth();

        // Draw border
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = x - width/2;
        borderRight = x + width/2;
        borderBottom = y - distToEntity;
        borderTop = borderBottom - height;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(borderLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(borderRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderBottom),
                borderPaint);
        Log.d("HEALTH", "draw: borderLeft : " + borderLeft + " borderBottom : " + borderBottom + " health percent : " + healthPointPercentage);
        // Draw health
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2*margin;
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth*healthPointPercentage;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(healthLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(healthRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthBottom),
                healthPaint);
    }
}
