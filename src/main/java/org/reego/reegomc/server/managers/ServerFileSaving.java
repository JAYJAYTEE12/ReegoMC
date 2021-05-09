package org.reego.reegomc.server.managers;

import org.reego.reegomc.ReegoMC;

import java.io.IOException;

public class ServerFileSaving {

    ReegoMC plugin;
    public ServerFileSaving(ReegoMC plugin){
        this.plugin = plugin;
    }

    public void saveServerFile(){
        try{
            plugin.getServerConfig().save(plugin.getServerFile());
        }catch(IOException e){
            e.printStackTrace();
            plugin.getServer().getConsoleSender().sendMessage("§cWas unable to save server data.");
        }
    }

}
