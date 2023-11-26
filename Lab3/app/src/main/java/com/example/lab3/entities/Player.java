package com.example.lab3.entities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;

import androidx.core.content.ContextCompat;

import com.example.lab3.R;
import com.example.lab3.actions.Action;
import com.example.lab3.actions.WaitAction;
import com.example.lab3.graphics.GameDisplay;
import com.example.lab3.graphics.SingleSheet;
import com.example.lab3.inventory.Armor;
import com.example.lab3.inventory.Consumable;
import com.example.lab3.inventory.Item;
import com.example.lab3.inventory.Weapon;
import com.example.lab3.logic.ActionsLog;
import com.example.lab3.logic.Game;
import com.example.lab3.logic.PathFinding;
import com.example.lab3.mapping.MapHolder;
import com.example.lab3.mapping.StaticObject;
import com.example.lab3.mapping.Tile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player extends Entity{
    private int dirX, dirY = 0;

    private int goldCount = 0;
    private int shroomsCount = 0;

    private Armor armor;
    private Weapon weapon;

    private List<Item> inventory = new ArrayList<>();
    private WaitAction waitAction = new WaitAction();

    private boolean turnTaken = false;
    //private PlayerState playerState;

    private Paint healthPaint;
    private Paint goldPaint;
    private Paint shroomsPaint;
    private Paint bgPaint;

    //private ActionsLog actionsLog;

    public Player(Context context, MapHolder mapHolder, int startX, int startY, int maxHealth/*, ActionsLog actionsLog*/) {
        super(context, mapHolder, startX, startY);
        //this.actionsLog = actionsLog;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        SingleSheet singleSheet = new SingleSheet(context, R.drawable.player);
        sprite = singleSheet.getSprite();
        mapHolder.setTilePassable(mapPosX, mapPosY, false);
        waitAction.setPrompt("Wait");
        this.armor = new Armor(0, 20, "Armor");
        this.weapon = new Weapon(1, 0, 20,false, false, "Sword");
        addItem(armor);
        addItem(weapon);
        healthPaint = new Paint();
        healthPaint.setColor(ContextCompat.getColor(context, R.color.green));
        healthPaint.setTextSize(35);
        goldPaint = new Paint();
        goldPaint.setColor(ContextCompat.getColor(context, R.color.gold));
        goldPaint.setTextSize(35);
        shroomsPaint = new Paint();
        shroomsPaint.setColor(ContextCompat.getColor(context, R.color.shroom));
        shroomsPaint.setTextSize(35);
        bgPaint = new Paint();
        bgPaint.setColor(ContextCompat.getColor(context, R.color.bg));
    }

    @Override
    public void update() {
        if(!(dirY == 0 && dirX == 0) && mapHolder.tileIsPassable(mapPosX + dirX, mapPosY + dirY)) {
            mapHolder.setTilePassable(mapPosX, mapPosY, true);
            positionX += dirX * MapHolder.TILE_WIDTH_PIXELS;
            positionY += dirY * MapHolder.TILE_HEIGHT_PIXELS;
            mapPosX += dirX;
            mapPosY += dirY;
            mapHolder.setTilePassable(mapPosX, mapPosY, false);
            dirX = 0;
            dirY = 0;
            turnTaken = true;
        }
        if(turnTaken){
            Game.sendUpdateRequest();
            turnTaken = false;
        }
    }

    @Override
    public void updateHealth(){
        super.updateHealth();
    }

    public void move(int dirX, int dirY){
        int stepX = mapPosX + dirX;
        int stepY = mapPosY + dirY;
        if(mapHolder.inBounds(stepX, stepY) && mapHolder.tileIsPassable(stepX, stepY)) {
            this.dirX = dirX;
            this.dirY = dirY;
        }
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas, (int) gameDisplay.gameToDisplayCoordinatesX(positionY), (int)gameDisplay.gameToDisplayCoordinatesY(positionX));
        canvas.drawRect(0, 0, 300, 150, bgPaint);
        canvas.drawText("Health : "  + health  + "\\" + maxHealth, 20, 40, healthPaint);
        canvas.drawText("Gold : " + goldCount, 20, 80, goldPaint);
        canvas.drawText("Shrooms : " + shroomsCount, 20, 120, shroomsPaint);
    }


    public List<Action> getActions(){
        List<Action> actions = new ArrayList<>();
        List<Action> tempUtilStorage = new ArrayList<>();
        actions.add(waitAction);
        for(int i = mapPosX - 1; i <= mapPosX+1; i++){
            for(int j = mapPosY - 1; j <= mapPosY+1; j++){
                Tile tile = mapHolder.getTile(i, j);
                if(tile != null){
                    Enemy enemy = tile.getEnemy();
                    StaticObject obj = tile.getObject();
                    if(enemy != null){
                        enemy.action.setExtraPrompt("(HP : " + enemy.getHealth() + "\\" +
                                enemy.getMaxHealth() + "+" + enemy.getProtection() + " | DMG : " + enemy.getDamage() +
                                ") [" + (i-mapPosX) + "," + (j-mapPosY) + "]");
                        actions.add(enemy.action);
                    }
                    if(obj != null){
                        obj.getAction().setExtraPrompt("[" + (i-mapPosX) + "," + (j-mapPosY) + "]");
                        tempUtilStorage.add(obj.getAction());
                    }
                }
            }
        }
        for(Action action : tempUtilStorage){
            actions.add(action);
        }
        return actions;
    }

    public void act(Action action){
        action.performAction(this);
        turnTaken = true;
    }

    public List<Item> getInventory(){
        return this.inventory;
    }

    public void setArmor(Armor armor){
        //actionsLog.stackLog("You equip Armor : " + armor.getDesc(), actionsLog.neutral);
        this.armor = armor;
    }

    public void setWeapon(Weapon weapon){
        //actionsLog.stackLog("You equip " + weapon.name + " : " + armor.getDesc(), actionsLog.neutral);
        this.weapon = weapon;
    }

    public void passTurn() {
        //actionsLog.stackLog("You wait.", actionsLog.neutral);
        turnTaken = true;
    }

    public void addItem(Item itemDropped) {
        //actionsLog.stackLog("You receive item : " + itemDropped.name + itemDropped.getAction().getExtraPrompt(), actionsLog.neutral);
        inventory.add(itemDropped);
        inventory.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if((o1 instanceof Weapon && (o2 instanceof Armor || o2 instanceof Consumable)) ||
                        (o1 instanceof Armor && o2 instanceof Consumable)){
                    return -1;
                }else if((o1 instanceof Armor && o2 instanceof Weapon) ||
                        (o1 instanceof Consumable && (o2 instanceof Weapon || o2 instanceof Armor))){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
    }

    public PathFinding.Pair getDamage(){
        weapon.reduceDurability();
        return new PathFinding.Pair(weapon.getDamage(), weapon.piercing);
    }

    public int getShroomsCount(){
        return shroomsCount;
    }

    public int getGoldCount(){
        return goldCount;
    }

    public void addGold(int gold){
        if(gold != 0) {
            //actionsLog.stackLog("You receive " + gold + " gold.", actionsLog.gold);
        }
        this.goldCount += gold;
    }

    public void addShrooms(int shrooms){
        if(shrooms != 0) {
            //actionsLog.stackLog("You spend " + shrooms + " shrooms.", actionsLog.shrooms);
        }
        this.shroomsCount += shrooms;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    @Override
    public void stackDamage(int damage){
        int damageToTake = Math.max(damage - armor.getProtection(), 0);
        armor.reduceDurability();
        super.stackDamage(damageToTake);
    }


    public Armor getArmor() {
        return this.armor;
    }

    public void heal(Consumable holder) {
        int toHeal = Math.min(holder.getHP(), maxHealth - health);
        //actionsLog.stackLog("You heal for " + toHeal + " points.", actionsLog.heal);
        health += toHeal;
        inventory.remove(holder);
        updateHealth();
    }

    //public ActionsLog getLogger(){
    //    return actionsLog;
    //}

    public void reduceGold(int toReduce){
        if(toReduce != 0) {
            //actionsLog.stackLog("You spend " + toReduce + " gold.", actionsLog.gold);
        }
        goldCount -= toReduce;
    }

    public void reduceShrooms(int price) {
        if(price != 0) {
            //actionsLog.stackLog("You spend " + price + " shrooms.", actionsLog.shrooms);
        }
        shroomsCount -= price;
    }
}
