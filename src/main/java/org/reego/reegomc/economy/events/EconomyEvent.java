package org.reego.reegomc.economy.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.reego.reegomc.economy.utils.EconomyUtils;
import org.reego.reegomc.server.utils.ServerUtils;

public class EconomyEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(EconomyUtils.getCoins(event.getPlayer()) == null){
            EconomyUtils.resetBalance(event.getPlayer());
            Bukkit.getServer().getConsoleSender().sendMessage("§aCreated profile for " + event.getPlayer().getName());
            event.getPlayer().teleport(ServerUtils.getSpawn());
        }
    }

}
