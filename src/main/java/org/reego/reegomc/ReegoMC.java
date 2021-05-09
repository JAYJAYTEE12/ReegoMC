package org.reego.reegomc;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.reego.reegomc.items.events.BlocksEvents;
import org.reego.reegomc.protection.commands.BuildCommand;
import org.reego.reegomc.protection.events.PreventionEvents;


public final class ReegoMC extends JavaPlugin {


    @Override
    public void onEnable() {
        registerClasses();
    }

    @Override
    public void onDisable() {

    }

    void registerClasses(){
        getCommand("build").setExecutor(new BuildCommand());
        new PreventionEvents(this);
        new BlocksEvents(this);
    }
}
