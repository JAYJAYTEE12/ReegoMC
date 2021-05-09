package org.reego.reegomc.economy.utils;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.reego.reegomc.ReegoMC;

public class EconomyUtils {

    private static ReegoMC plugin;

    public EconomyUtils(ReegoMC plugin) {
        this.plugin = plugin;
    }

    @Nullable
    public static Integer getCoins(Player player) {
        if(plugin.getPlayerConfig().get(player.getUniqueId().toString() + ".Coins") == null){
            return null;
        }
        return plugin.getPlayerConfig().getInt(player.getUniqueId().toString() + ".Coins");
    }

    public static void setBalance(Player player, int amount) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Coins", amount);
    }

    public static void addBalance(Player player, int amount) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Coins", getCoins(player) + amount);
    }

    public static void removeBalance(Player player, int amount) {
        plugin.getPlayerConfig().set(player.getUniqueId().toString() + ".Coins", getCoins(player) - amount);
    }

    public static void resetBalance(Player player) {
        setBalance(player, 0);
    }

}
