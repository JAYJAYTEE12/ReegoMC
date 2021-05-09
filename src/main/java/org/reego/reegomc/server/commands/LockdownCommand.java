package org.reego.reegomc.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.server.utils.ServerUtils;

public class LockdownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("reego.admin")){
            sender.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }
        ServerUtils.lockdown(!ServerUtils.getLockdown());
        return true;
    }
}
