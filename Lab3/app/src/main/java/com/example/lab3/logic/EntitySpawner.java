package com.example.lab3.logic;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.entities.Enemy;
import com.example.lab3.entities.Player;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;

import java.util.ArrayList;
import java.util.List;

public class EntitySpawner {
    private final int enemyMaxSpawnRate = 20;
    private final int enemyMinSpawnRate = 10;
    private final int enemyNumberCap = 20;
    private final int minSpawnBoundX = 5;
    private final int maxSpawnBoundX = 15;
    private final int minSpawnBoundY = 10;
    private final int maxSpawnBoundY = 20;
    private Context context;
    private MapHolder mapHolder;
    private GameDisplay gameDisplay;
    private Player player;
    private int enemySpawnRate;
    private List<Enemy> enemies = new ArrayList<>();

    public EntitySpawner(Context context, MapHolder mapHolder, Player player, GameDisplay gameDisplay){
        this.context = context;
        this.mapHolder = mapHolder;
        this.gameDisplay = gameDisplay;
        this.player = player;
        spawnEnemy();
    }

    public void update(){
        if(enemySpawnRate == 0){
            if(enemies.size() <= enemyNumberCap){
                spawnEnemy();
            }
        }else{
            enemySpawnRate--;
        }
        for(Enemy enemy : enemies){
            enemy.update();
        }
    }

    public void draw(Canvas canvas){
        for(Enemy enemy : enemies){
            enemy.draw(canvas, gameDisplay);
        }
    }

    private void spawnEnemy(){
        int spawnX = player.getMapPosX() + (Game.rnd.nextInt(1) - 2)*Game.rnd.nextInt(maxSpawnBoundX) + minSpawnBoundX;
        int spawnY = player.getMapPosY() + (Game.rnd.nextInt(1) - 2)*Game.rnd.nextInt(maxSpawnBoundY) + minSpawnBoundY;
        while (!mapHolder.inBounds(spawnX, spawnY) || !mapHolder.tileIsPassable(spawnX, spawnY)){
            spawnX = player.getMapPosX() + (Game.rnd.nextInt(1) - 2)*Game.rnd.nextInt(maxSpawnBoundX) + minSpawnBoundX;
            spawnY = player.getMapPosY() + (Game.rnd.nextInt(1) - 2)*Game.rnd.nextInt(maxSpawnBoundY) + minSpawnBoundY;
        }
        Enemy enemy = new Enemy(context, mapHolder, 5, spawnX, spawnY, 20, player);
        enemies.add(enemy);
        enemySpawnRate = Game.rnd.nextInt(enemyMaxSpawnRate) + enemyMinSpawnRate;
    }

    private void removeEnemy(){

    }
}
