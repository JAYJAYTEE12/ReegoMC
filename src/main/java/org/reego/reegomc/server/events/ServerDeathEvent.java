package org.reego.reegomc.server.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.reego.reegomc.server.utils.ServerUtils;

public class ServerDeathEvent implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        Player player = (Player) event.getPlayer();
        player.teleport(ServerUtils.getSpawn());
    }
}
