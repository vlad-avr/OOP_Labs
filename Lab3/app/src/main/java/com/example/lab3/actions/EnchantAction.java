package com.example.lab3.actions;

import com.example.lab3.entities.Player;
import com.example.lab3.inventory.Enchantment;
import com.example.lab3.logic.ActionsLog;

public class EnchantAction extends Action{
    private Enchantment holder;

    public EnchantAction(Enchantment holder){
        this.holder = holder;
    }

    @Override
    public void performAction(Player player){
        Enchantment.EnchantmentType eType = holder.getType();
        switch (eType){
            case ENCHANT_FOR_CRITS:
                player.getWeapon().moreCrit();
                player.getLogger().stackLog("You enchant your Weapon with eldritch magic...", ActionsLog.SHROOMS);
                break;
            case ENCHANT_FOR_DODGE:
                player.getArmor().moreDodge();
                player.getLogger().stackLog("You enchant your Armor with eldritch magic...", ActionsLog.SHROOMS);
                break;
            case MEND_ARMOR:
                player.getArmor().mend();
                player.getLogger().stackLog("You mend your Armor by unfathomable gibberish...", ActionsLog.SHROOMS);
                break;
            case MEND_WEAPON:
                player.getWeapon().mend();
                player.getLogger().stackLog("You mend your Weapon by unfathomable gibberish...", ActionsLog.SHROOMS);
            case ENDCHANT:
            default:
                player.setDeathMessage("You find peace in the sweet embrace of death. You feel relieved..");
                player.getLogger().stackLog("Your mind is overwhelmed by knowledge that you SHOULD NOT HAVE LEARNT...", ActionsLog.SHROOMS);
        }
        player.reduceShrooms(holder.getPrice());
    }
}
