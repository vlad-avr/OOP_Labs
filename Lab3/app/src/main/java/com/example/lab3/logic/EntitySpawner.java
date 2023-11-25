package com.example.lab3.logic;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.entities.Enemy;
import com.example.lab3.entities.Player;
import com.example.lab3.graphics.EnemySheet;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.mapping.MapHolder;
import com.example.lab3.mapping.Tile;

import java.util.ArrayList;
import java.util.List;

public class EntitySpawner {
    private final int enemyMaxSpawnRate = 20;
    private final int enemyMinSpawnRate = 10;
    private final int enemyNumberCap = 20;
    private final int minSpawnBoundX = 10;
    private final int maxSpawnBoundX = 10;
    private final int minSpawnBoundY = 10;
    private final int maxSpawnBoundY = 10;
    private Context context;
    private MapHolder mapHolder;
    private GameDisplay gameDisplay;
    private Player player;
    private int enemySpawnRate;
    private EnemySheet enemySheet;
    private List<Enemy> enemies = new ArrayList<>();

    public EntitySpawner(Context context, MapHolder mapHolder, Player player, GameDisplay gameDisplay){
        this.context = context;
        this.mapHolder = mapHolder;
        this.gameDisplay = gameDisplay;
        this.player = player;
        enemySheet = new EnemySheet(context);
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
        List<Enemy> toRemove = new ArrayList<>();
        for(Enemy enemy : enemies){
            enemy.updateHealth();
            if(!enemy.isDead()) {
                enemy.update();
            }else{
                toRemove.add(enemy);
            }
        }
        for(Enemy enemyToRemove : toRemove){
            removeEnemy(enemyToRemove);
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
        Tile.TileType tileType = mapHolder.getTile(spawnX, spawnY).getTileType();
        Enemy enemy = null;
        double roll = Game.rnd.nextDouble();
        switch (tileType){
            case GROUND:
                if(roll < 0.3){
                    enemy = initSlime(spawnX, spawnY);
                }else if(roll < 0.5){
                    enemy = initGoblin(spawnX, spawnY);
                }else if(roll < 0.7){
                    enemy = initRockSpider(spawnX, spawnY);
                }else if(roll < 0.9){
                    enemy = initBushMaster(spawnX, spawnY);
                }else if(roll < 0.95){
                    enemy = initMage(spawnX, spawnY);
                }else if(roll < 0.985){
                    enemy = initGuard(spawnX, spawnY);
                }else {
                    enemy = initAngryTree(spawnX, spawnY);
                }
                break;
            case ROCK:
                if(roll < 0.5){
                    enemy = initRockSpider(spawnX, spawnY);
                }else if(roll < 0.6){
                    enemy = initGuard(spawnX, spawnY);
                }else if(roll < 0.7){
                    enemy = initMage(spawnX, spawnY);
                }else if(roll < 0.8){
                    enemy = initGoblin(spawnX, spawnY);
                }else if(roll < 0.975){
                    enemy = initSlime(spawnX, spawnY);
                }else{
                    enemy = initGolem(spawnX, spawnY);
                }
                break;
            case SAND:
                if(roll < 0.2){
                    enemy = initSlime(spawnX, spawnY);
                }else if(roll < 0.6){
                    enemy = initHeadCrab(spawnX, spawnY);
                }else{
                    enemy = initBug(spawnX, spawnY);
                }
                break;
        }
        if(enemy != null) {
            enemies.add(enemy);
        }
        enemySpawnRate = Game.rnd.nextInt(enemyMaxSpawnRate) + enemyMinSpawnRate;
    }

    private void removeEnemy(Enemy enemy){
        enemy.removeFromMap();
        enemies.remove(enemy);
    }

    private Enemy initGuard(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.GUARD), player);
        enemy.setAttackRange(2);
        enemy.setSpeed(1);
        enemy.setRange(15);
        enemy.setDamageDelt(4);
        enemy.setMaxHealth(20);
        enemy.setFumbleChance(0.3);
        enemy.setAttackFailChance(0.6);
        enemy.setName("Guard");
        return enemy;
    }
    private Enemy initGoblin(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.GOBLIN), player);
        enemy.setAttackRange(1);
        enemy.setSpeed(2);
        enemy.setRange(20);
        enemy.setDamageDelt(1);
        enemy.setMaxHealth(3);
        enemy.setFumbleChance(0.5);
        enemy.setAttackFailChance(0.2);
        enemy.setName("Goblin");
        return enemy;
    }
    private Enemy initAngryTree(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.ANGRY_TREE), player);
        enemy.setAttackRange(2);
        enemy.setSpeed(1);
        enemy.setRange(10);
        enemy.setDamageDelt(8);
        enemy.setMaxHealth(40);
        enemy.setFumbleChance(0.8);
        enemy.setAttackFailChance(0.5);
        enemy.setName("Angry Tree");
        return enemy;
    }
    private Enemy initBushMaster(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.BUSHMASTER), player);
        enemy.setAttackRange(5);
        enemy.setSpeed(3);
        enemy.setRange(25);
        enemy.setDamageDelt(1);
        enemy.setMaxHealth(3);
        enemy.setFumbleChance(0.6);
        enemy.setAttackFailChance(0.8);
        enemy.setName("Bush Master");
        return enemy;
    }
    private Enemy initBug(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.BUG), player);
        enemy.setAttackRange(1);
        enemy.setSpeed(1);
        enemy.setRange(10);
        enemy.setDamageDelt(3);
        enemy.setMaxHealth(7);
        enemy.setFumbleChance(0.5);
        enemy.setAttackFailChance(0.3);
        enemy.setName("Bug");
        return enemy;
    }
    private Enemy initGolem(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.GOLEM), player);
        enemy.setAttackRange(1);
        enemy.setSpeed(1);
        enemy.setRange(5);
        enemy.setDamageDelt(5);
        enemy.setMaxHealth(50);
        enemy.setFumbleChance(0.8);
        enemy.setAttackFailChance(0.5);
        enemy.setName("Golem");
        return enemy;
    }
    private Enemy initMage(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.MAGE), player);
        enemy.setAttackRange(4);
        enemy.setSpeed(1);
        enemy.setRange(15);
        enemy.setDamageDelt(5);
        enemy.setMaxHealth(2);
        enemy.setFumbleChance(0.05);
        enemy.setAttackFailChance(0.8);
        enemy.setName("Mage");
        return enemy;
    }
    private Enemy initSlime(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.SLIME), player);
        enemy.setAttackRange(1);
        enemy.setSpeed(2);
        enemy.setRange(20);
        enemy.setDamageDelt(2);
        enemy.setMaxHealth(2);
        enemy.setFumbleChance(0.5);
        enemy.setAttackFailChance(0.5);
        enemy.setName("Slime");
        return enemy;
    }
    private Enemy initRockSpider(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.ROCK_SPIDER), player);
        enemy.setAttackRange(1);
        enemy.setSpeed(2);
        enemy.setRange(25);
        enemy.setDamageDelt(1);
        enemy.setMaxHealth(1);
        enemy.setFumbleChance(0.05);
        enemy.setAttackFailChance(0.2);
        enemy.setName("Rock Spider");
        return enemy;
    }
    private Enemy initHeadCrab(int posX, int posY){
        Enemy enemy = new Enemy(context, mapHolder, posX, posY, enemySheet.getSprite(EnemySheet.ENEMIES.SKULL_CRAB), player);
        enemy.setAttackRange(1);
        enemy.setSpeed(1);
        enemy.setRange(10);
        enemy.setDamageDelt(5);
        enemy.setMaxHealth(10);
        enemy.setFumbleChance(0.1);
        enemy.setAttackFailChance(0.6);
        enemy.setName("Skull Crab");
        return enemy;
    }




}
