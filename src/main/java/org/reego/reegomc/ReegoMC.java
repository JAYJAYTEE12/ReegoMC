package org.reego.reegomc;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.reego.reegomc.economy.commands.CoinsCommand;
import org.reego.reegomc.economy.commands.SaveEconomyCommand;
import org.reego.reegomc.economy.events.EconomyEvent;
import org.reego.reegomc.economy.managers.PlayerFileLoop;
import org.reego.reegomc.economy.utils.EconomyUtils;
import org.reego.reegomc.items.events.BlocksEvents;
import org.reego.reegomc.protection.commands.BuildCommand;
import org.reego.reegomc.protection.events.CommandPreventionEvents;
import org.reego.reegomc.protection.events.PreventionEvents;

import java.io.File;


public final class ReegoMC extends JavaPlugin {

    private File playerFile = new File(getDataFolder(), "players.yml");
    private FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

    @Override
    public void onEnable() {
        if (!playerFile.exists()) saveResource("players.yml", false);
        BukkitTask task = new PlayerFileLoop(this).runTaskTimer(this, 20*300, 20*300);
        registerCommandsAndEvents();
        registerUtils();
    }

    @Override
    public void onDisable() {

    }

    void registerCommandsAndEvents() {
        // Commands
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("coins").setExecutor(new CoinsCommand());
        new SaveEconomyCommand(this);
        // Events
        getServer().getPluginManager().registerEvents(new EconomyEvent(), this);
        getServer().getPluginManager().registerEvents(new CommandPreventionEvents(), this);
        new PreventionEvents(this);
        new BlocksEvents(this);
    }

    void registerUtils(){
        new EconomyUtils(this);
    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public File getPlayerFile(){
        return playerFile;
    }

}
