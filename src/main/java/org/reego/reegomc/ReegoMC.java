package org.reego.reegomc;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.reego.reegomc.economy.commands.CoinsCommand;
import org.reego.reegomc.economy.commands.SaveEconomyCommand;
import org.reego.reegomc.economy.events.EconomyEvent;
import org.reego.reegomc.economy.managers.PlayerFileLoop;
import org.reego.reegomc.economy.managers.PlayerFileSaving;
import org.reego.reegomc.economy.utils.EconomyUtils;
import org.reego.reegomc.items.events.BlocksEvents;
import org.reego.reegomc.items.scrolls.commands.ScrollCommand;
import org.reego.reegomc.items.scrolls.events.ScrollRightClickEvent;
import org.reego.reegomc.protection.commands.BuildCommand;
import org.reego.reegomc.protection.events.CommandPreventionEvents;
import org.reego.reegomc.protection.events.PreventionEvents;
import org.reego.reegomc.server.commands.*;
import org.reego.reegomc.server.events.ServerDeathEvent;
import org.reego.reegomc.server.events.ServerPlayerJoinEvent;
import org.reego.reegomc.server.managers.ServerFileLoop;
import org.reego.reegomc.server.managers.ServerFileSaving;
import org.reego.reegomc.server.utils.ServerUtils;

import java.io.File;


public final class ReegoMC extends JavaPlugin {

    private File playerFile = new File(getDataFolder(), "players.yml");
    private FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

    private File serverFile = new File(getDataFolder(), "server.yml");
    private FileConfiguration serverConfig = YamlConfiguration.loadConfiguration(serverFile);

    @Override
    public void onEnable() {
        if (!playerFile.exists()) saveResource("players.yml", false);
        if (!serverFile.exists()) saveResource("server.yml", false);
        BukkitTask task = new PlayerFileLoop(this).runTaskTimer(this, 20*300, 20*300);
        BukkitTask task2 = new ServerFileLoop(this).runTaskTimer(this, 20*300, 20*300);
        registerCommandsAndEvents();
        registerUtils();
    }

    @Override
    public void onDisable() {
        PlayerFileSaving playerFileSaving = new PlayerFileSaving(this);
        ServerFileSaving serverFileSaving = new ServerFileSaving(this);
        playerFileSaving.savePlayerFile();
        serverFileSaving.saveServerFile();
    }

    void registerCommandsAndEvents() {
        // Commands
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("lockdown").setExecutor(new LockdownCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("wipe").setExecutor(new WipeCommand());
        getCommand("scroll").setExecutor(new ScrollCommand());
        getCommand("goto").setExecutor(new GoToCommand());
        new SaveEconomyCommand(this);
        new SaveServerCommand(this);
        // Events
        getServer().getPluginManager().registerEvents(new EconomyEvent(), this);
        getServer().getPluginManager().registerEvents(new CommandPreventionEvents(), this);
        getServer().getPluginManager().registerEvents(new ServerPlayerJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new ServerDeathEvent(), this);
        new PreventionEvents(this);
        new BlocksEvents(this);
        new ScrollRightClickEvent(this);
    }

    void registerUtils(){
        new EconomyUtils(this);
        new ServerUtils(this);
    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public File getPlayerFile(){
        return playerFile;
    }

    public FileConfiguration getServerConfig() {
        return serverConfig;
    }

    public File getServerFile(){
        return serverFile;
    }

}
