package com.example.lab3.mapping;

import com.example.lab3.actions.UtilityAction;
import com.example.lab3.graphics.PlantSheet;
import com.example.lab3.graphics.RockSheet;
import com.example.lab3.inventory.ItemFactory;
import com.example.lab3.logic.Game;

public class StaticObjectFactory {

    public static StaticObject makePlant(PlantSheet.PLANTS plantType, PlantSheet plantSheet){
        StaticObject obj;
        UtilityAction action;
        switch (plantType){
            case BUSH:
                obj = new StaticObject(plantSheet.getSprite(plantType), "Harvest Bush");
                action = obj.getAction();
                action.setChopRequired(true);
                action.setGoldDropBounds(0, 0);
                action.setShroomsDropBounds(0, 0);
                action.setItemDropChance(0.5);
                action.setItemDrop(ItemFactory.makeConsumable(Game.rnd.nextInt(5) + 1));
                break;
            case PINE:
                obj = new StaticObject(plantSheet.getSprite(plantType), "Harvest Pine");
                action = obj.getAction();
                action.setChopRequired(true);
                action.setGoldDropBounds(0, 0);
                action.setShroomsDropBounds(0, 2);
                action.setItemDropChance(0.1);
                action.setItemDrop(ItemFactory.makeConsumable(Game.rnd.nextInt(10) + 1));
                break;
            case TREE:
                obj = new StaticObject(plantSheet.getSprite(plantType), "Harvest Tree");
                action = obj.getAction();
                action.setChopRequired(true);
                action.setGoldDropBounds(0, 0);
                action.setGoldShroomsChance(0.2);
                action.setShroomsDropBounds(0, 4);
                action.setItemDropChance(0.6);
                action.setItemDrop(ItemFactory.makeConsumable(Game.rnd.nextInt(8) + 1));
                break;
            case BRIAR:
                obj = new StaticObject(plantSheet.getSprite(plantType), "Harvest Briar");
                action = obj.getAction();
                action.setGoldDropBounds(0, 0);
                action.setShroomsDropBounds(0, 0);
                action.setItemDropChance(0.7);
                action.setItemDrop(ItemFactory.makeConsumable(Game.rnd.nextInt(3) + 1));
                break;
            case SHROOM:
            default:
                obj = new StaticObject(plantSheet.getSprite(plantType), "Harvest Shroom");
                action = obj.getAction();
                action.setGoldDropBounds(0, 0);
                action.setGoldShroomsChance(0.5);
                action.setShroomsDropBounds(0, 1);
                break;
        }
        return obj;
    }

    public static StaticObject makeRock(RockSheet.ROCKS rockType, RockSheet rockSheet){
        StaticObject obj;
        UtilityAction action;
        switch (rockType){
            case BIG:
                obj = new StaticObject(rockSheet.getSprite(rockType), "Mine large rock");
                action = obj.getAction();
                action.setMineRequired(true);
                action.setGoldDropChance(0.05);
                action.setGoldDropBounds(0, 5);
                action.setGoldShroomsChance(0.05);
                action.setShroomsDropBounds(0, 2);
                action.setItemDropChance(0.1);
                action.setItemDrop(ItemFactory.makeConsumable(Game.rnd.nextInt(4) + 1));
                break;
            case GOLD:
                obj = new StaticObject(rockSheet.getSprite(rockType), "Mine gold");
                action = obj.getAction();
                action.setMineRequired(true);
                action.setGoldDropChance(1.0);
                action.setGoldDropBounds(2, 30);
                action.setShroomsDropBounds(0, 0);
                break;
            case RUBY:
                obj = new StaticObject(rockSheet.getSprite(rockType), "Mine ruby gem");
                action = obj.getAction();
                action.setMineRequired(true);
                action.setGoldDropChance(1.0);
                action.setGoldDropBounds(10, 60);
                action.setShroomsDropBounds(0, 0);
                break;
            case MUSHROOM:
                obj = new StaticObject(rockSheet.getSprite(rockType), "Mine overgrown rock");
                action = obj.getAction();
                action.setMineRequired(true);
                action.setGoldDropBounds(0, 0);
                action.setGoldShroomsChance(0.8);
                action.setShroomsDropBounds(2, 15);
                action.setItemDropChance(0.1);
                action.setItemDrop(ItemFactory.makeConsumable(Game.rnd.nextInt(4) + 1));
                break;
            case SMOL:
            default:
                obj = new StaticObject(rockSheet.getSprite(rockType), "Mine small rock");
                action = obj.getAction();
                action.setGoldDropChance(0.5);
                action.setGoldDropBounds(0, 5);
                action.setGoldShroomsChance(0.2);
                action.setShroomsDropBounds(0, 1);
                break;
        }
        return obj;
    }

}
