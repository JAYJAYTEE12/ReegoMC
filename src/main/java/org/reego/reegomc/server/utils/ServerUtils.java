package org.reego.reegomc.server.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
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

    @Nullable
    public static Location getSpawn() {
        if(plugin.getServerConfig().get("spawn") == null){
            return null;
        }

        double x = plugin.getServerConfig().getDouble("spawn.x");
        double y = plugin.getServerConfig().getDouble("spawn.y");
        double z = plugin.getServerConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getPlayerConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getPlayerConfig().getDouble("spawn.pitch");

        return new Location(Bukkit.getWorld("spawn"), x, y, z, (float) yaw, (float) pitch);
    }

    public static void setSpawn(Location location){
        plugin.getServerConfig().set("spawn.x", location.getX());
        plugin.getServerConfig().set("spawn.y", location.getY());
        plugin.getServerConfig().set("spawn.z", location.getZ());
        plugin.getServerConfig().set("spawn.yaw", location.getYaw());
        plugin.getServerConfig().set("spawn.pitch", location.getPitch());
        ServerFileSaving serverFileSaving = new ServerFileSaving(plugin);
        serverFileSaving.saveServerFile();
    }

}
