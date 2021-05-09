package org.reego.reegomc.economy.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.reego.reegomc.ReegoMC;
import org.reego.reegomc.economy.managers.PlayerFileSaving;
import org.reego.reegomc.enums.MESSAGES;

public class SaveEconomyCommand implements CommandExecutor {

    ReegoMC plugin;
    public SaveEconomyCommand(ReegoMC plugin){
        this.plugin = plugin;
        plugin.getCommand("reloadEconomy").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("reego.admin")){
            sender.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }

        PlayerFileSaving playerFileSaving = new PlayerFileSaving(plugin);
        playerFileSaving.savePlayerFile();

        sender.sendMessage("§aSuccessfully reloaded the economy files!");

        return true;
    }
}
