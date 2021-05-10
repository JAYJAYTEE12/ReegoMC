package org.reego.reegomc.items.scrolls.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.reego.reegomc.ReegoMC;
import org.reego.reegomc.libs.NBTUtils1_12;
import org.reego.reegomc.server.utils.ServerUtils;

public class ScrollRightClickEvent implements Listener {

    ReegoMC plugin;
    public ScrollRightClickEvent(ReegoMC plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player player = event.getPlayer();
            ItemStack item = player.getInventory().getItemInMainHand();
            NBTUtils1_12 nbt = new NBTUtils1_12();
            if(!nbt.hasTag(item, "scroll")) return;
            if(nbt.getString(item, "scroll").equalsIgnoreCase("secret")){
                // TODO: Add travel cord maker. Add particle effects. Add delay. Add messages. Add check if already clicked scroll
                player.teleport(new Location(Bukkit.getWorld("world"), -114.727, 67.30894, 820.351, 0.5F, 90F));
                item.setAmount(item.getAmount() - 1);
                player.getInventory().setItemInMainHand(item);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(ServerUtils.getSpawn());
                    }
                }, 20*30);
            }
        }
    }

}
