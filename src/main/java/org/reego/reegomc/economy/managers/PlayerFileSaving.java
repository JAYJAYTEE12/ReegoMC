package org.reego.reegomc.economy.managers;

import org.reego.reegomc.ReegoMC;

import java.io.IOException;

public class PlayerFileSaving {

    ReegoMC plugin;
    public PlayerFileSaving(ReegoMC plugin){
        this.plugin = plugin;
    }

    public void savePlayerFile(){
        try{
            plugin.getPlayerConfig().save(plugin.getPlayerFile());
        }catch(IOException e){
            e.printStackTrace();
            plugin.getServer().getConsoleSender().sendMessage("§cWas unable to save player data.");
        }
    }

}
