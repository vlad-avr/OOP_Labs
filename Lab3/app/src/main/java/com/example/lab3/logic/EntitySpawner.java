package com.example.lab3.logic;

import android.content.Context;

import com.example.lab3.entities.Enemy;
import com.example.lab3.entities.Player;
import com.example.lab3.mapping.MapHolder;

import java.util.ArrayList;
import java.util.List;

public class EntitySpawner {
    private final int enemyMaxSpawnRate = 10;
    private final int enemyMinSpawnRate = 5;
    private final int enemyNumberCap = 20;
    private final int minSpawnBoundX = 5;
    private final int maxSpawnBoundX = 15;
    private final int minSpawnBoundY = 10;
    private final int maxSpawnBoundY = 20;
    private Context context;
    private MapHolder mapHolder;
    private Player player;
    private int enemySpawnRate;
    private List<Enemy> enemies = new ArrayList<>();

    public EntitySpawner(Context context, MapHolder mapHolder, Player player){
        this.context = context;
        this.mapHolder = mapHolder;
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
    }

    private void spawnEnemy(){
        int spawnX = Game.rnd.nextInt(maxSpawnBoundX) + minSpawnBoundX;
        int spawnY = Game.rnd.nextInt(maxSpawnBoundY) + minSpawnBoundY;
        while (!mapHolder.tileIsPassable(spawnY, spawnX)){
            spawnX = Game.rnd.nextInt(maxSpawnBoundX) + minSpawnBoundX;
            spawnY = Game.rnd.nextInt(maxSpawnBoundY) + minSpawnBoundY;
        }
        Enemy enemy = new Enemy(context, mapHolder, 5, spawnX, spawnY, 20);
        enemies.add(enemy);
        enemySpawnRate = Game.rnd.nextInt(enemyMaxSpawnRate) + enemyMinSpawnRate;
    }

    private void removeEnemy(){

    }
}
