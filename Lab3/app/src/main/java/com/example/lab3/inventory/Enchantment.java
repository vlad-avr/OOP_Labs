package com.example.lab3.inventory;

import com.example.lab3.actions.EnchantAction;
import com.example.lab3.entities.Player;

public class Enchantment {
    public static enum EnchantmentType{
        MEND_ARMOR,
        MEND_WEAPON,
        ENCHANT_FOR_CRITS,
        ENCHANT_FOR_DODGE,
        ENDCHANT
    }

    private String name;
    private EnchantmentType type;
    private int price;

    private EnchantAction action;

    public Enchantment(EnchantmentType type){
        this.type = type;
        switch (type){
            case MEND_WEAPON:
                name = "Scroll of Weapon Mending";
                break;
            case MEND_ARMOR:
                name = "Scroll of Armor Mending";
                break;
            case ENCHANT_FOR_DODGE:
                name = "Scroll of Dodging";
                break;
            case ENCHANT_FOR_CRITS:
                name = "Scroll of Critical hits";
                break;
            case ENDCHANT:
                name = "Summon Nath-Horthath";
                price = 200;
                break;
        }
        action = new EnchantAction(this);
    }

    public EnchantAction getAction(){
        return action;
    }

    public String getName(){
        return this.name;
    }

    public EnchantmentType getType(){
        return this.type;
    }

    public int getPrice(){
        return this.price;
    }

    public void calculatePrice(Player player){
        switch (type){
            case MEND_ARMOR:
                price = (player.getArmor().protection+1)*5 + player.getArmor().getDurability()*2;
                break;
            case MEND_WEAPON:
                price = player.getWeapon().getDamageRaw()*3 + player.getWeapon().piercing*4 + player.getWeapon().getDurability()*2;
                break;
            case ENCHANT_FOR_CRITS:
                price = player.getWeapon().getDamageRaw()*8;
                break;
            case ENCHANT_FOR_DODGE:
                price = (player.getArmor().protection+1)*8;
                break;
            default:
                price = 200;
        }
        action.setPrompt("Buy " + name + " " + " for " + price + " shrooms");
    }
}
