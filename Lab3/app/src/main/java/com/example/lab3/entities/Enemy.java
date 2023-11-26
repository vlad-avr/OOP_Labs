package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;

import com.example.lab3.actions.EntityAction;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.Sprite;
import com.example.lab3.inventory.Item;
import com.example.lab3.inventory.ItemFactory;
import com.example.lab3.logic.Game;
import com.example.lab3.logic.PathFinding;
import com.example.lab3.mapping.MapHolder;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity{

    private double range;

    protected EntityAction action;
    protected PathFinding.Pair wanderDir = new PathFinding.Pair(0, 1);
    private int attackRange;
    private int speed;
    private double fumbleChance = 0.0;
    private double attackFailChance = 0.0;
    private int minGold;
    private int maxGold;
    private int minShrooms;
    private int maxShrooms;
    private List<String> droppable = new ArrayList<>();
    private double dropChance;
    private int protection;
    private String name;
    private boolean agroed = false;
    private Player player;

    public Enemy(Context context, MapHolder mapHolder, int posX, int posY, Sprite sprite, Player player){
        super(context, mapHolder, posX, posY);
        this.sprite = sprite;
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(this);
        this.action = new EntityAction(this);
        this.player = player;
    }

    public void setName(String name){
        action.setPrompt("Attack " + name);
        this.name = name;
    }

    public String getName(){ return name;}

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void setFumbleChance(double fumbleChance){
        this.fumbleChance = fumbleChance;
    }

    public void setRange(double range){
        this.range = range;
    }

    public void setAttackRange(int attackRange){
        this.attackRange = attackRange;
    }

    public void setDamageDelt(int damageDelt){
        this.damageDelt = damageDelt;
    }

    public void setAttackFailChance(double chance){
        this.attackFailChance = chance;
    }
    public void setProtection(int protection){this.protection = protection;}
    public int getProtection(){return this.protection;}
    public int getDamage(){return this.damageDelt;}
    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionY), (int)gameDisplay.gameToDisplayCoordinatesY(positionX));
        super.draw(canvas, gameDisplay);
    }

    @Override
    public void update() {
        super.update();
        if(agroed){
            if (GameObject.getMapDistBetweenObjects(player, this) <= attackRange) {
                attack();
            }else {
                PathFinding.Pair playerPos = new PathFinding.Pair(player.getMapPosX(), player.getMapPosY());
                PathFinding.Pair stepPos = PathFinding.aStarSearch(mapHolder, new PathFinding.Pair(mapPosX, mapPosY), playerPos, speed);
                if (stepPos != null) {
                    move(stepPos.first, stepPos.second);
                }
            }
        }else{
            wanderDir = getRandMove();
            move(mapPosX + wanderDir.first, mapPosY + wanderDir.second);
        }
        if(GameObject.getDistanceBetweenObjects(player, this) <= range){
            if(!agroed){
                player.getLogger().stackLog(name + " is coming for you!", player.getLogger().DANGER);
            }
            agroed = true;
        }else{
            if(agroed){
                player.getLogger().stackLog(name + " is no longer after you!", player.getLogger().ATTENTION);
            }
            agroed = false;
        }
    }

    public int calculateDamage(PathFinding.Pair damage){
        return Math.abs(Math.min(Math.max(protection - damage.second, 0) - damage.first, 0));
    }

    protected void move(int x, int y){
        double roll = Game.rnd.nextDouble();
        if(roll < fumbleChance){
            return;
        }
        if(!mapHolder.inBounds(x,y) || !mapHolder.tileIsPassable(x, y)){
            return;
        }
        mapHolder.setTilePassable(mapPosX, mapPosY, true);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(null);
        positionX = x * MapHolder.TILE_WIDTH_PIXELS;
        positionY = y * MapHolder.TILE_HEIGHT_PIXELS;
        mapPosX = x;
        mapPosY = y;
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(this);
    }

    protected void attack(){
        double roll = Game.rnd.nextDouble();
        if(roll > attackFailChance) {
            player.stackDamage(damageDelt);
        }
    }

    protected PathFinding.Pair getRandMove(){
        if(Game.rnd.nextBoolean()){
            int dir = Game.rnd.nextInt(4);
            switch (dir){
                case 0:
                    return new PathFinding.Pair(0, 1);
                case 1:
                    return new PathFinding.Pair(1, 0);
                case 2:
                    return new PathFinding.Pair(0, -1);
                case 3:
                    return new PathFinding.Pair(-1, 0);
            }
        }
        return wanderDir;
    }

    public void setGoldDropBounds(int min, int max){
        minGold = min;
        maxGold = max;
    }

    public void setShroomsDropBounds(int min, int max){
        minShrooms = min;
        maxShrooms = max;
    }

    public void addDroppable(String id){
        droppable.add(id);
    }

    public void setDropChance(double chance){
        this.dropChance = chance;
    }

    public void removeFromMap(){
        int goldDropped = maxGold - Game.rnd.nextInt(Math.max(maxGold - minGold, 1));
        int shroomsDropped = maxShrooms - Game.rnd.nextInt(Math.max(maxShrooms - minShrooms, 1));
        double roll = Game.rnd.nextDouble();
        if(roll <= dropChance && droppable.size() != 0) {
            Item itemDropped = null;
            String prompt = droppable.get(Game.rnd.nextInt(droppable.size()));
            switch (prompt){
                case "S1":
                    itemDropped = ItemFactory.makeWeapon(1, ItemFactory.SWORD);
                    break;
                case "S2":
                    itemDropped = ItemFactory.makeWeapon(2, ItemFactory.SWORD);
                    break;
                case "S3":
                    itemDropped = ItemFactory.makeWeapon(3, ItemFactory.SWORD);
                    break;
                case "S4":
                    itemDropped = ItemFactory.makeWeapon(4, ItemFactory.SWORD);
                    break;
                case "A1":
                    itemDropped = ItemFactory.makeWeapon(1, ItemFactory.AXE);
                    break;
                case "A2":
                    itemDropped = ItemFactory.makeWeapon(2, ItemFactory.AXE);
                    break;
                case "A3":
                    itemDropped = ItemFactory.makeWeapon(3, ItemFactory.AXE);
                    break;
                case "A4":
                    itemDropped = ItemFactory.makeWeapon(4, ItemFactory.AXE);
                    break;
                case "H1":
                    itemDropped = ItemFactory.makeWeapon(1, ItemFactory.HAMMER);
                    break;
                case "H2":
                    itemDropped = ItemFactory.makeWeapon(2, ItemFactory.HAMMER);
                    break;
                case "H3":
                    itemDropped = ItemFactory.makeWeapon(3, ItemFactory.HAMMER);
                    break;
                case "H4":
                    itemDropped = ItemFactory.makeWeapon(4, ItemFactory.HAMMER);
                    break;
                case "P1":
                    itemDropped = ItemFactory.makeArmor(1, 0.7);
                    break;
                case "P2":
                    itemDropped = ItemFactory.makeArmor(1, 0.8);;
                    break;
                case "P3":
                    itemDropped = ItemFactory.makeArmor(1, 0.9);;
                    break;
                case "P4":
                    itemDropped = ItemFactory.makeArmor(1, 1.0);;
                    break;
                case "C1":
                    itemDropped = ItemFactory.makeConsumable(1+Game.rnd.nextInt(5));
                    break;
                case "C2":
                    itemDropped = ItemFactory.makeConsumable(3+Game.rnd.nextInt(5));
                    break;
                case "C3":
                    itemDropped = ItemFactory.makeConsumable(6+Game.rnd.nextInt(5));
                    break;
                case "C4":
                    itemDropped = ItemFactory.makeConsumable(10+Game.rnd.nextInt(10));
                    break;
                default:
                    itemDropped = null;
            }
            if(itemDropped != null) {
                player.addItem(itemDropped);
            }
        }
        player.addGold(goldDropped);
        player.addShrooms(shroomsDropped);
        mapHolder.getTile(mapPosX, mapPosY).setEnemy(null);
        mapHolder.getTile(mapPosX, mapPosY).setPassable(true);
    }

}
