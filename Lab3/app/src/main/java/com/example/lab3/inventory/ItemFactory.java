package com.example.lab3.inventory;

import com.example.lab3.logic.Game;

public class ItemFactory {

    private static final int minDmg = 0;
    private static final int maxDmgT1 = 5;
    private static final int maxDmgT2 = 15;
    private static final int maxDmgT3 = 25;
    private static final int maxDmgT4 = 40;
    private static final int minPrc = -1;
    private static final int maxPrcT1 = 5;
    private static final int maxPrcT2 = 10;
    private static final int maxPrcT3 = 15;
    private static final int maxPrcT4 = 20;
    private static final int minProt = -1;
    private static final int maxProtT1 = 3;
    private static final int maxProtT2 = 7;
    private static final int maxProtT3 = 10;
    private static final int maxProtT4 = 15;
    public static final int SWORD = 0;
    public static final int AXE = 1;
    public static final int HAMMER = 2;
    public static Weapon makeWeapon(int tier, int type){
        String name = "Sword";
        boolean canChop = false;
        boolean canMine = false;
        double prcModifier = 1;
        if(type == AXE){
            canChop = true;
            prcModifier = 0.8;
            name = "Axe";
        }else if(type == HAMMER){
            canMine = true;
            prcModifier = 0.8;
            name = "Hammer";
        }
        switch (tier){
            case 2:
                return new Weapon(maxDmgT2 - Game.rnd.nextInt(maxDmgT2-maxDmgT1),
                        (int)(prcModifier*(maxPrcT2 - Game.rnd.nextInt(maxPrcT2-maxPrcT1))),
                        canChop, canMine, name);
            case 3:
                return new Weapon(maxDmgT3 - Game.rnd.nextInt(maxDmgT3-maxDmgT2),
                        (int)(prcModifier*(maxPrcT3 - Game.rnd.nextInt(maxPrcT3-maxPrcT2))),
                        canChop, canMine, name);
            case 4:
                return new Weapon(maxDmgT4 - Game.rnd.nextInt(maxDmgT4-maxDmgT3),
                        (int)(prcModifier*(maxPrcT4 - Game.rnd.nextInt(maxPrcT4-maxPrcT3))),
                        canChop, canMine, name);
            case 1:
            default:
                return new Weapon(maxDmgT1 - Game.rnd.nextInt(maxDmgT1-minDmg),
                        maxPrcT1 - Game.rnd.nextInt(maxPrcT1-minPrc),
                        canChop, canMine, name);
        }
    }

    public static Armor makeArmor(int tier, double erg){
        switch (tier){
            case 2:
                return new Armor(maxProtT2 - Game.rnd.nextInt(maxProtT2-maxProtT1), erg, "Armor");
            case 3:
                return new Armor(maxProtT3 - Game.rnd.nextInt(maxProtT3-maxProtT2), erg, "Armor");
            case 4:
                return new Armor(maxProtT4 - Game.rnd.nextInt(maxProtT4-maxProtT3), erg, "Armor");
            case 1:
            default:
                return new Armor(maxProtT1 - Game.rnd.nextInt(maxProtT1-minProt), erg, "Armor");
        }
    }

    public static Consumable makeConsumable(int HP){
        if(HP < 3){
            return new Consumable("Berries", HP);
        }else if(HP < 5){
            return new Consumable("Fruit", HP);
        }else if(HP < 10){
            return new Consumable("Healing Herbs", HP);
        }else {
            return new Consumable("Healing Potion", HP);
        }
    }
}
