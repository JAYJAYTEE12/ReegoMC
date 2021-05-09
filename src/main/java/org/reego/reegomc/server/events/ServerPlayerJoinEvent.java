package org.reego.reegomc.server.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.reego.reegomc.server.utils.ServerUtils;

public class ServerPlayerJoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(ServerUtils.getLockdown()){
            if(!event.getPlayer().hasPermission("reego.helper")){
                event.getPlayer().kickPlayer("§cOur game server isn't available to the public right now.");
            }
        }
    }

}
