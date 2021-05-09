package org.reego.reegomc.protection.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreventionEvents implements Listener {

    String[] cantContain = new String[] { ":","minecraft","bukkit","?","spigot","me" };

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        for(String s : cantContain){
            if(event.getMessage().contains(s)){
                event.setCancelled(true);
                event.getPlayer().sendMessage("§fUnknown command. Type \"/help\" for help.");
                return;
            }
        }
    }

}
