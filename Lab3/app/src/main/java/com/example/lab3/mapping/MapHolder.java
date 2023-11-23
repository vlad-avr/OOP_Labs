package com.example.lab3.mapping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.PlantSheet;
import com.example.lab3.graphics.RockSheet;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.graphics.TileSheet;
import com.example.lab3.logic.Game;

import java.security.SecureRandom;

public class MapHolder {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 150;
    public static final int TILE_NUM = WIDTH*HEIGHT;
    public final int START_X = 0;
    public final int START_Y = 0;
    private static final int WALL = 0;
    private static final int GROUND = 1;
    private static final int ROCK = 2;
    private static final int SAND = 3;
    private static final int WATER = 4;
    private static final double groundFillChance = 0.3;
    private static final double rockFillChance = 0.15;
    private static final double sandFillChance = 0.6;
    private final PlantSheet plantSheet;
    private final RockSheet rockSheet;
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    private SecureRandom rnd = Game.rnd;
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
                            if (inBounds(k, l)) {
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
                        if (inBounds(k, l)) {
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
            while(getTileVal(randX, randY) != GROUND){
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
                boolean toFill = false;
                switch (mapPlan[i][j]){
                    case GROUND:
                        if(rnd.nextFloat() < groundFillChance){
                            toFill = true;
                        }
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.GROUND);
                        mapVisual[i][j] = tile;
                        break;
                    case ROCK:
                        if(rnd.nextFloat() < rockFillChance){
                            toFill = true;
                        }
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.ROCK);
                        mapVisual[i][j] = tile;
                        break;
                    case SAND:
                        if(rnd.nextFloat() < sandFillChance){
                            toFill = true;
                        }
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.SAND);
                        mapVisual[i][j] = tile;
                        break;
                    case WATER:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.WATER);
                        tile.setPassable(false);
                        mapVisual[i][j] = tile;
                        break;
                    case WALL:
                        tile = new Tile(tileSheet, getRectByIndex(i, j), Tile.TileType.WALL);
                        mapVisual[i][j] = tile;
                        break;

                }
                if(toFill){
                    genTileFiller(mapVisual[i][j]);
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
        float roll = rnd.nextFloat();
        switch (tile.getTileType()){
            case GROUND:
                if(roll < 0.3){
                    tile.addPlant(plantSheet, PlantSheet.PLANTS.PINE);
                }else if(roll < 0.6){
                    tile.addPlant(plantSheet, PlantSheet.PLANTS.BUSH);
                }else if(roll < 0.8){
                    tile.addRock(rockSheet, RockSheet.ROCKS.SMOL);
                }else if(roll < 0.9){
                    tile.addPlant(plantSheet, PlantSheet.PLANTS.TREE);
                }else{
                    tile.addPlant(plantSheet, PlantSheet.PLANTS.SHROOM);
                }
                break;
            case ROCK:
                if(roll < 0.3){
                    tile.addRock(rockSheet, RockSheet.ROCKS.SMOL);
                }else if(roll < 0.85){
                    tile.addRock(rockSheet, RockSheet.ROCKS.BIG);
                }else if(roll < 0.95){
                    tile.addRock(rockSheet, RockSheet.ROCKS.GOLD);
                }else if(roll < 0.975){
                    tile.addRock(rockSheet, RockSheet.ROCKS.RUBY);
                }else{
                    tile.addRock(rockSheet, RockSheet.ROCKS.MUSHROOM);
                }
                break;
            case SAND:
                if(roll < 0.5){
                    tile.addRock(rockSheet, RockSheet.ROCKS.SMOL);
                }else{
                    tile.addPlant(plantSheet, PlantSheet.PLANTS.BRIAR);
                }
                break;
            default:
                break;
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
    public int getTileVal(int i, int j){
        if(!inBounds(i,j)){
            return WALL;
        }
        return mapPlan[i][j];
    }

    public Tile getTile(int i, int j){
        if(inBounds(i, j)){
            return mapVisual[i][j];
        }
        return null;
    }
    public void fillTile(int i, int j, int condition, int toFill){
        if(mapPlan[i][j] == condition) {
            curTilesFilled++;
            mapPlan[i][j] = toFill;
        }
    }

    public boolean inBounds(int i, int j){
        return (i < WIDTH && j < HEIGHT && i >= 0 && j >= 0);
    }

    public boolean tileIsPassable(int i, int j){
        return mapVisual[i][j].isPassable();
    }

    public void setTilePassable(int i, int j, boolean passable){
        mapVisual[i][j].setPassable(passable);
    }

 /*   public void setTilePassingSprite(int i, int j, Sprite sprite){
        if(sprite == null) {
            mapVisual[i][j].setPassable(true);
        }else{
            mapVisual[i][j].setPassable(false);
        }
        mapVisual[i][j].setPassingSprite(sprite);
    }
*/

}
