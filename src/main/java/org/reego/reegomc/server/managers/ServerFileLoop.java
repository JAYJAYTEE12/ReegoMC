package org.reego.reegomc.server.managers;

import org.bukkit.scheduler.BukkitRunnable;
import org.reego.reegomc.ReegoMC;

public class ServerFileLoop extends BukkitRunnable {
    ReegoMC plugin;
    public ServerFileLoop(ReegoMC plugin){
        this.plugin = plugin;
    }

    public void run(){
        ServerFileSaving serverFileSaving = new ServerFileSaving(plugin);
        serverFileSaving.saveServerFile();
    }
}
