package com.example.lab3.mapping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.PlantSheet;
import com.example.lab3.graphics.RockSheet;
import com.example.lab3.graphics.TileSheet;

import java.security.SecureRandom;

public class MapHolder {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 150;
    public static final int TILE_NUM = WIDTH*HEIGHT;
    public static final int START_X = 50;
    public static final int START_Y = 20;
    private static final int WALL = 0;
    private static final int GROUND = 1;
    private static final int ROCK = 2;
    private static final int SAND = 3;
    private static final int WATER = 4;
    private static final double fillChance = 0.5;
    private final PlantSheet plantSheet;
    private final RockSheet rockSheet;
    public static final int TILE_WIDTH_PIXELS = 10;
    public static final int TILE_HEIGHT_PIXELS = 10;
    private SecureRandom rnd = new SecureRandom();
    private int[][] mapPlan;
    private Tile[][] mapVisual;
    private final double wallsToFloorsRatio = 0.4;
    private final double waterToTilesRatio = 0.1;
    /*private final double filledSand = 0.3;
    private final double filledWater = 0.2;*/
    private int curTilesFilled = 0;
    private TileSheet tileSheet;
    private Bitmap mapBitmap;

    public MapHolder(Context context){
        tileSheet = new TileSheet(context);
        plantSheet = new PlantSheet(context);
        rockSheet = new RockSheet(context);
    }
    private void initMap(){
        mapPlan = new int[WIDTH][HEIGHT];
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                if(rnd.nextFloat() < wallsToFloorsRatio) {
                    mapPlan[i][j] = GROUND;
                }else {
                    mapPlan[i][j] = ROCK;
                }
            }
        }
    }

    private void cellularAutomata(int iterations){
        for(int iter = 0; iter < iterations; iter++){
            int[][] mapCopy = new int[WIDTH][HEIGHT];
            for(int row = 0; row < WIDTH; row++){
                System.arraycopy(mapPlan[row], 0, mapCopy[row], 0, HEIGHT);
                //mapCopy[row] = mapPlan[row].clone();
            }
            for(int i = 0; i < WIDTH; i++){
                for(int j = 0; j < HEIGHT; j++){
                    int wallCount = 0;
                    for(int k = i-1; k <= i+1; k++){
                        for(int l = j-1; l <= j+1;l++) {
                            if (k < WIDTH && l < HEIGHT && k >= 0 && l >= 0) {
                                if (!(k == i && l == j)) {
                                    if (mapCopy[k][l] == ROCK) {
                                        wallCount++;
                                    }
                                }
                            } else {
                                wallCount++;
                            }
                        }
                    }
                    //Log.d("Automata", String.valueOf(wallCount));
                    if(wallCount > 4){
                        mapPlan[i][j] = ROCK;
                    }else{
                        mapPlan[i][j] = GROUND;
                    }
                }
            }
        }
    }

    private void fillSand(){
        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                if(mapPlan[i][j] == WATER){
                    continue;
                }
                int sandNum = 0;
                for(int k = i-1; k <= i+1; k++){
                    for(int l = j-1; l <= j+1; l++){
                        if (k < WIDTH && l < HEIGHT && k >= 0 && l >= 0) {
                            if (!(k == i && l == j)) {
                                if (mapPlan[k][l] == WATER) {
                                    mapPlan[i][j] = SAND;
                                    l = j+1;
                                    k = i+1;
                                }else if(mapPlan[k][l] == SAND){
                                    sandNum++;
                                }
                            }
                        }
                    }
                }
                if(sandNum > 4){
                    mapPlan[i][j] = SAND;
                }
            }
        }
    }
    public void generateMapPlan(){
        initMap();
        cellularAutomata(15);
        Walker walker = new Walker(this, TILE_NUM/WIDTH, WATER, GROUND, rnd);
        curTilesFilled = 0;
        while(!filledEnough(waterToTilesRatio, TILE_NUM)){
            int randX = WIDTH/2;
            int randY = HEIGHT/2;
            while(getTile(randX, randY) != GROUND){
                randX = rnd.nextInt(WIDTH);
                randY = rnd.nextInt(HEIGHT);
            }
            walker.walk(randX, randY);
        }
        fillSand();
        drawMapTiles();
    }

    private void drawMapTiles(){
        mapVisual = new Tile[WIDTH][HEIGHT];

        for(int i = 0; i < WIDTH; i++){
            for(int j = 0; j < HEIGHT; j++){
                Tile tile;
                switch (mapPlan[i][j]){
                    case GROUND:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.GROUND);
                        mapVisual[i][j] = tile;
                        break;
                    case ROCK:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.ROCK);
                        mapVisual[i][j] = tile;
                        break;
                    case SAND:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.SAND);
                        mapVisual[i][j] = tile;
                        break;
                    case WATER:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.WATER);
                        mapVisual[i][j] = tile;
                        break;
                    case WALL:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.WALL);
                        mapVisual[i][j] = tile;
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

    private void genTileFiller(Tile tile){

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
    public void fillTile(int i, int j, int condition, int toFill){
        if(mapPlan[i][j] == condition) {
            curTilesFilled++;
            mapPlan[i][j] = toFill;
        }
    }


}
