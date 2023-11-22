package com.example.rpg_attempt.mapping;

import java.security.SecureRandom;

public class MapHolder {
    public final int WIDTH = 100;
    public final int HEIGHT = 150;
    public final int TILE_NUM = WIDTH*HEIGHT;
    public final int START_X = 50;
    public final int START_Y = 20;
    public final int WALL = 0;
    public final int GROUND = 1;
    public final int ROCK = 2;
    public final int SAND = 3;
    public final int WATER = 4;
    private SecureRandom rnd = new SecureRandom();
    private int[][] mapPlan;
    private final double filledMap = 0.8;
    private final double filledRock = 0.1;
    private final double filledSand = 0.3;
    private final double filledWater = 0.2;
    private int curTilesFilled = 0;
    private void initMap(){
        mapPlan = new int[WIDTH][HEIGHT];
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                mapPlan[i][j] = 0;
            }
        }
    }
    public void generateMapPlan(){
        initMap();
        Walker walker = new Walker(this, 20, GROUND, rnd);
        int randX = START_X;
        int randY = START_Y;
        walker.walk(START_X, START_Y);
        while(!filledEnough(filledMap, TILE_NUM)){
            randX = rnd.nextInt(WIDTH);
            randY = rnd.nextInt(HEIGHT);
            while(isEmpty(randX, randY)){
                randX = rnd.nextInt(WIDTH);
                randY = rnd.nextInt(HEIGHT);
            }
            walker.walk(randX, randY);
        }
        int mapTilesFilled = curTilesFilled;
        curTilesFilled = 0;
        walker.setFiller(ROCK);
        walker.setMaxSteps(5);
        while (!filledEnough(filledRock, mapTilesFilled)){
            randX = rnd.nextInt(WIDTH);
            randY = rnd.nextInt(HEIGHT);
            while(isEmpty(randX, randY)){
                randX = rnd.nextInt(WIDTH);
                randY = rnd.nextInt(HEIGHT);
            }
            walker.walk(randX, randY);
        }
        curTilesFilled = 0;
        walker.setFiller(SAND);
        walker.setMaxSteps(10);
        while (!filledEnough(filledSand, mapTilesFilled)){
            randX = rnd.nextInt(WIDTH);
            randY = rnd.nextInt(HEIGHT);
            while(isEmpty(randX, randY)){
                randX = rnd.nextInt(WIDTH);
                randY = rnd.nextInt(HEIGHT);
            }
            walker.walk(randX, randY);
        }
        mapTilesFilled = curTilesFilled;
        curTilesFilled = 0;
        walker.setFiller(WATER);
        walker.setMaxSteps(5);
        while (!filledEnough(filledWater, mapTilesFilled)){
            randX = rnd.nextInt(WIDTH);
            randY = rnd.nextInt(HEIGHT);
            while(getTile(randX, randY) != SAND){
                randX = rnd.nextInt(WIDTH);
                randY = rnd.nextInt(HEIGHT);
            }
            walker.walk(randX, randY);
        }
    }
    public boolean isEmpty(int i, int j){
        if(mapPlan[i][j] == 0){
            return true;
        }
        return false;
    }

    public boolean filledEnough(double fillerBound, int tileNum){
        if((double)curTilesFilled/(double)tileNum <= fillerBound){
            return false;
        }
        return true;
    }
    public int getTile(int i, int j){
        if(i < 0 || j < 0 || i >= WIDTH || j >= HEIGHT){
            return WALL;
        }
        return mapPlan[i][j];
    }
    public void fillTile(int i, int j, int filler){
        mapPlan[i][j] = filler;
        curTilesFilled++;
    }


}
