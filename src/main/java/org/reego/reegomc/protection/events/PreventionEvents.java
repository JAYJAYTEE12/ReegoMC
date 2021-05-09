package org.reego.reegomc.protection.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.reego.reegomc.ReegoMC;
import org.reego.reegomc.protection.commands.BuildCommand;
import org.reego.reegomc.protection.enums.DISALLOWED_BLOCKS;

public class PreventionEvents implements Listener {

    ReegoMC plugin;

    public PreventionEvents(ReegoMC plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent event) {
        if (!BuildCommand.getBuildMode(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlace(BlockPlaceEvent event) {
        if (!BuildCommand.getBuildMode(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) {
            return;
        }
        if (!BuildCommand.getBuildMode(event.getPlayer())) {
            for (DISALLOWED_BLOCKS mat : DISALLOWED_BLOCKS.values()) {
                if (block.getType() == Material.getMaterial(String.valueOf(mat))) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onFood(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        event.setCancelled(true);
        event.setFoodLevel(20);
        player.setFoodLevel(20);
    }

}
