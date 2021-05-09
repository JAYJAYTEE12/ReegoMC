package org.reego.reegomc.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.reego.reegomc.ReegoMC;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.server.managers.ServerFileSaving;

public class SaveServerCommand implements CommandExecutor {
    ReegoMC plugin;
    public SaveServerCommand(ReegoMC plugin){
        this.plugin = plugin;
        plugin.getCommand("reloadServer").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("reego.admin")){
            sender.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }

        ServerFileSaving serverFileSaving = new ServerFileSaving(plugin);
        serverFileSaving.saveServerFile();

        sender.sendMessage("§aSuccessfully reloaded the server files!");

        return true;
    }
}
