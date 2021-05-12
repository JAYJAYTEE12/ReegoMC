package org.reego.reegomc.items.events;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.reego.reegomc.ReegoMC;
import org.reego.reegomc.libs.ItemBuilder;

public class BlocksEvents implements Listener {

    ReegoMC plugin;

    public BlocksEvents(ReegoMC plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            return;
        }

        if (player.getWorld().getName().equals("build")) {
            Material o = event.getBlock().getType();
            if(o.equals(Material.CROPS)){
                byte data = event.getBlock().getData();
                if(data != 7) return;
                player.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemBuilder(Material.WHEAT, 1)
                        .setLore(1, "&f&lCOMMON")
                        .addNBT("type", "common").build());
                event.getBlock().setType(Material.AIR);

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        event.getBlock().setType(o);
                        event.getBlock().setData((byte)7);
                    }
                }, 20);
                return;
            }
            //Bukkit.broadcastMessage(o.toString());
            byte data = event.getBlock().getData();

            player.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemBuilder(event.getBlock().getType(), 1, data)
                    .setLore(1, "&f&lCOMMON")
                    .addNBT("type", "common").build());
            event.getBlock().setType(Material.BEDROCK);
            player.getWorld().playEffect(event.getBlock().getLocation(), Effect.STEP_SOUND, event.getBlock().getType());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    event.getBlock().setType(o);
                    event.getBlock().setData(data);
                }
            }, 20);
        }
    }

}
