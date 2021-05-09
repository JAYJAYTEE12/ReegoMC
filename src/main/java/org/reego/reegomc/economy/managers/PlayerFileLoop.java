package org.reego.reegomc.economy.managers;

import org.bukkit.scheduler.BukkitRunnable;
import org.reego.reegomc.ReegoMC;

public class PlayerFileLoop extends BukkitRunnable {
    ReegoMC plugin;
    public PlayerFileLoop(ReegoMC plugin){
        this.plugin = plugin;
    }

    public void run(){
        PlayerFileSaving playerFileSaving = new PlayerFileSaving(plugin);
        playerFileSaving.savePlayerFile();
    }
}
