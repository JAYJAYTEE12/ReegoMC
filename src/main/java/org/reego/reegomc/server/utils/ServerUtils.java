package org.reego.reegomc.server.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.reego.reegomc.ReegoMC;
import org.reego.reegomc.server.managers.ServerFileSaving;

public class ServerUtils {

    private static ReegoMC plugin;
    public ServerUtils(ReegoMC plugin){
        this.plugin = plugin;
    }

    public static boolean getLockdown(){
        return plugin.getServerConfig().getBoolean("lockdown");
    }

    public static void lockdown(boolean a){
        if(a){
            plugin.getServerConfig().set("lockdown", true);
            ServerFileSaving serverFileSaving = new ServerFileSaving(plugin);
            serverFileSaving.saveServerFile();

            Bukkit.getServer().broadcastMessage("§cThe server is going into lockdown. §7(Please wait)");
            for(Player player : Bukkit.getOnlinePlayers()){
                if(!player.hasPermission("reego.helper")){
                    player.kickPlayer("§cOur game server isn't available to the public right now.");
                }
            }
            return;
        }else{
            plugin.getServerConfig().set("lockdown", false);
            ServerFileSaving serverFileSaving = new ServerFileSaving(plugin);
            serverFileSaving.saveServerFile();
            Bukkit.getServer().broadcastMessage("§aThe server is going out of lockdown. §7(Please wait)");
        }

    }

}
