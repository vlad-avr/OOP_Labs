package com.example.lab3.actions;

import com.example.lab3.entities.Player;
import com.example.lab3.inventory.Enchantment;

public class EnchantAction extends Action{
    private Enchantment holder;

    public EnchantAction(Enchantment holder){
        this.holder = holder;
        setPrompt("Buy " + holder.getName() + " " + " for " + holder.getPrice() + " gold");
    }

    @Override
    public void performAction(Player player){
        Enchantment.EnchantmentType eType = holder.getType();
        switch (eType){
            case ENCHANT_FOR_CRITS:
                player.getWeapon().moreCrit();
                break;
            case ENCHANT_FOR_DODGE:
                player.getArmor().moreDodge();
                break;
            case MEND_ARMOR:
                player.getArmor().mend();
                break;
            case MEND_WEAPON:
                player.getWeapon().mend();
            case ENDCHANT:
            default:
                System.out.println("END");
        }
    }
}
