package com.example.lab3.actions;

import android.util.Log;

import com.example.lab3.entities.Player;
import com.example.lab3.inventory.Consumable;
import com.example.lab3.inventory.Item;
import com.example.lab3.logic.Game;
import com.example.lab3.mapping.StaticObject;

public class UtilityAction extends Action{

    private int minGoldDrop;
    private int maxGoldDrop;
    private int minShroomsDrop;
    private int maxShroomsDrop;
    private Consumable itemDrop = null;

    private double goldDropChance = 0.0;
    private double shroomsDropChance = 0.0;
    private double itemDropChance = 0.0;

    private boolean chopRequired = false;
    private boolean mineRequired = false;

    private StaticObject holder;

    public UtilityAction(StaticObject holder){
        super();
        this.holder = holder;
    }

    public void setGoldDropBounds(int min, int max){
        this.minGoldDrop = min;
        this.maxGoldDrop = max;
    }

    public void setShroomsDropBounds(int min, int max){
        this.maxShroomsDrop = max;
        this.minShroomsDrop = min;
    }

    public void setItemDrop(Consumable item){
        this.itemDrop = item;
    }

    public void setChopRequired(boolean b){
        chopRequired = b;
    }

    public void setGoldDropChance(double goldDropChance){
        this.goldDropChance = goldDropChance;
    }
    public void setGoldShroomsChance(double shroomsDropChance){
        this.shroomsDropChance = shroomsDropChance;
    }
    public void setItemDropChance(double itemDropChance){
        this.itemDropChance = itemDropChance;
    }


    public void setMineRequired(boolean b){
        mineRequired = b;
    }

    public int getGoldDrop(){
        double roll = Game.rnd.nextDouble();
        if(roll > goldDropChance){
            return 0;
        }
        return maxGoldDrop - Game.rnd.nextInt(maxGoldDrop - (minGoldDrop - 1));
    }

    public int getShroomsDrop(){
        double roll = Game.rnd.nextDouble();
        if(roll > shroomsDropChance){
            return 0;
        }
        return maxShroomsDrop - Game.rnd.nextInt(maxShroomsDrop - (minShroomsDrop - 1));
    }

    public Consumable getItemDrop(){
        double roll = Game.rnd.nextDouble();
        if(roll > itemDropChance){
            return null;
        }
        return this.itemDrop;
    }

    public boolean isChopRequired(){
        return chopRequired;
    }

    public boolean isMineRequired(){
        return mineRequired;
    }

    @Override
    public void performAction(Player player) {
        super.performAction(player);
        Consumable item = getItemDrop();
        if(item != null){
            player.addItem(item);
        }
        player.addGold(getGoldDrop());
        player.addShrooms(getShroomsDrop());
        holder.destroySelf();
    }
}
