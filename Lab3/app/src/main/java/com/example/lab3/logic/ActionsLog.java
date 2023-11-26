package com.example.lab3.logic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.graphics.GameDisplay;

import java.util.ArrayList;
import java.util.List;

public class ActionsLog {

    public class Pair{
        public String log;
        public Paint paint;
        public Pair(String log, Paint paint){
            this.paint = paint;
            this.log = log;
        }
    }
    private List<Pair> logs = new ArrayList<>();
    private final int maxLogs = 15;

    public Paint neutral;
    public Paint shrooms;
    public Paint gold;
    public Paint heal;
    public Paint attention;
    public Paint danger;


    public void stackLog(String log, Paint paint){
        logs.add(new Pair(log, paint));
        if(logs.size() >= maxLogs){
            logs.remove(0);
        }
    }

    public void clearLogs(){
        logs.clear();
    }

    public void init(Context context){
        neutral = new Paint();
        neutral.setColor(ContextCompat.getColor(context, R.color.white));
        shrooms = new Paint();
        shrooms.setColor(ContextCompat.getColor(context, R.color.shroom));
        gold = new Paint();
        gold.setColor(ContextCompat.getColor(context, R.color.gold));
        heal = new Paint();
        heal.setColor(ContextCompat.getColor(context, R.color.green));
        attention = new Paint();
        attention.setColor(ContextCompat.getColor(context, R.color.armor));
        danger = new Paint();
        danger.setColor(ContextCompat.getColor(context, R.color.red));
    }

    public void draw(Canvas canvas){
        for(int i = 0; i < logs.size(); i++){
            Pair toLog = logs.get(i);
            canvas.drawText(toLog.log, 20, 300*(i+1), toLog.paint);
        }
    }

}
