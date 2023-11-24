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
    private int enemySpawnRate;
    private List<Enemy> enemies = new ArrayList<>();

    public EntitySpawner(Context context, MapHolder mapHolder, Player player){
        
    }

    public void update(){

    }
}
