package com.example.lab3.logic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;

import java.util.ArrayList;
import java.util.List;

public class ActionsLog {

    private List<Pair<String, Paint>> logs = new ArrayList<>();

    public static int NEUTRAL = 0;
    public static int SHROOMS = 1;
    public static int GOLD = 2;
    public static int HEAL = 3;
    public static int ATTENTION = 4;
    public static int DANGER = 5;

    private final int maxLogs = 15;

    public Paint[] paints = new Paint[6];


    public void stackLog(String log, int paintId){
        logs.add(new Pair(log, paints[paintId]));
        if(logs.size() >= maxLogs){
            logs.remove(0);
        }
    }

    public void clearLogs(){
        if (logs.size() != 0) {
            logs.clear();
        }
    }

    public void init(Context context){
        paints[0] = new Paint();
        paints[0].setColor(ContextCompat.getColor(context, R.color.white));
        paints[1] = new Paint();
        paints[1].setColor(ContextCompat.getColor(context, R.color.shroom));
        paints[2] = new Paint();
        paints[2].setColor(ContextCompat.getColor(context, R.color.gold));
        paints[3] = new Paint();
        paints[3].setColor(ContextCompat.getColor(context, R.color.green));
        paints[4] = new Paint();
        paints[4].setColor(ContextCompat.getColor(context, R.color.armor));
        paints[5] = new Paint();
        paints[5].setColor(ContextCompat.getColor(context, R.color.red));
        for(Paint paint : paints){
            paint.setTextSize(28);
        }
    }

    public void draw(Canvas canvas){
        for(int i = 0; i < logs.size(); i++){
            Pair toLog = logs.get(i);
            canvas.drawText((String) toLog.first, 20, 300+30*i, (Paint) toLog.second);
        }
    }

}
