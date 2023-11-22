package com.example.rpg_attempt.mapping;

import java.security.SecureRandom;

public class Walker {

    private MapHolder mapHolder;
    private int maxSteps;
    private int filler;
    private SecureRandom rnd;

    public Walker(MapHolder mapHolder, int maxSteps, int filler, SecureRandom rnd){
        this.filler = filler;
        this.maxSteps = maxSteps;
        this.mapHolder = mapHolder;
        this.rnd = rnd;
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
                case 1:
                    i--;
                case 2:
                    j++;
                case 3:
                    j--;
            }
            if(i < mapHolder.WIDTH && j < mapHolder.HEIGHT && i >= 0 && j >= 0){
                mapHolder.fillTile(i, j, filler);
            }else{
                break;
            }
        }
    }
}
