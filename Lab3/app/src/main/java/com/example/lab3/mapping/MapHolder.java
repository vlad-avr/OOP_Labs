package com.example.lab3.mapping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.TileSheet;

import java.security.SecureRandom;

public class MapHolder {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 150;
    public static final int TILE_NUM = WIDTH*HEIGHT;
    public static final int START_X = 50;
    public static final int START_Y = 20;
    private static final int WALL = 0;
    private static final int GROUND = 1;
    private static final int ROCK = 2;
    private static final int SAND = 3;
    private static final int WATER = 4;
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    private SecureRandom rnd = new SecureRandom();
    private int[][] mapPlan;
    private Tile[][] mapVisual;
    private final double filledMap = 0.8;
    private final double filledRock = 0.1;
    private final double filledSand = 0.3;
    private final double filledWater = 0.2;
    private int curTilesFilled = 0;
    private TileSheet tileSheet;
    private Bitmap mapBitmap;

    public MapHolder(Context context){
        tileSheet = new TileSheet(context);
    }
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
        drawMapTiles();
    }

    private void drawMapTiles(){
        mapVisual = new Tile[WIDTH][HEIGHT];

        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                switch (mapPlan[i][j]){
                    case GROUND:
                        mapVisual[i][j] = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.GROUND);
                        break;
                    case ROCK:
                        mapVisual[i][j] = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.ROCK);
                        break;
                    case SAND:
                        mapVisual[i][j] = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.SAND);
                        break;
                    case WATER:
                        mapVisual[i][j] = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.WATER);
                        break;
                    case WALL:
                        mapVisual[i][j] = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.WALL);
                        break;
                }
            }
        }
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                WIDTH*TILE_WIDTH_PIXELS,
                HEIGHT*TILE_HEIGHT_PIXELS,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < WIDTH; iRow++) {
            for (int iCol = 0; iCol < HEIGHT; iCol++) {
                mapVisual[iRow][iCol].draw(mapCanvas);
            }
        }
    }
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(
                mapBitmap,
                gameDisplay.getGameRect(),
                gameDisplay.DISPLAY_RECT,
                null
        );
    }
    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol*TILE_WIDTH_PIXELS,
                idxRow*TILE_HEIGHT_PIXELS,
                (idxCol + 1)*TILE_WIDTH_PIXELS,
                (idxRow + 1)*TILE_HEIGHT_PIXELS
        );
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