package com.example.lab3.mapping;

import java.security.SecureRandom;

public class Walker {

    private MapHolder mapHolder;
    private int maxSteps;
    private int filler;
    private int cond;
    private SecureRandom rnd;

    public Walker(MapHolder mapHolder, int maxSteps, int filler, int condition, SecureRandom rnd){
        this.filler = filler;
        this.maxSteps = maxSteps;
        this.mapHolder = mapHolder;
        this.rnd = rnd;
        this.cond = condition;
    }

    public void setMaxSteps(int maxSteps){
        this.maxSteps = maxSteps;
    }
    public void setFiller(int filler){
        this.filler = filler;
    }

    public void walk(int startX, int startY) {
        int stepCount = 0;
        int i = startX;
        int j = startY;
        while (stepCount < maxSteps) {
            int dir = rnd.nextInt(4);
            switch (dir){
                case 0:
                    i++;
                    break;
                case 1:
                    i--;
                    break;
                case 2:
                    j++;
                    break;
                case 3:
                    j--;
                    break;
            }
            if(i < mapHolder.WIDTH && j < mapHolder.HEIGHT && i >= 0 && j >= 0){
                mapHolder.fillTile(i, j, cond, filler);
                stepCount++;
            }else{
                break;
            }
        }
    }
}
