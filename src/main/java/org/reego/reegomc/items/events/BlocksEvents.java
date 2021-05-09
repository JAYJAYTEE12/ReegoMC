package org.reego.reegomc.items.events;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
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

        if (player.getWorld().getName().equals("world")) {
            Material o = event.getBlock().getType();
            byte data = event.getBlock().getData();

            player.getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemBuilder(event.getBlock().getType(), 1, data)
            .setLore(1, "&f&lCOMMON").build());
            event.getBlock().setType(Material.AIR);
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
