package org.reego.reegomc.server.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;

public class TeleportUtils {

    public static boolean goToWorld(String targetWorld, Player player){
        World world = Bukkit.getWorld(targetWorld);
        if(world == null){
            return false;
        }

        player.teleport(world.getSpawnLocation());
        return true;
    }

}
