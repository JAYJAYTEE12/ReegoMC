package org.reego.reegomc.server.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.server.utils.ServerUtils;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("reego.admin")){
            sender.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }

        if(!(sender instanceof Player)){
            sender.sendMessage(MESSAGES.NOT_PLAYER.message);
            return true;
        }
        Player player = (Player) sender;

        ServerUtils.setSpawn(player.getLocation());
        sender.sendMessage("§aSuccessfully set spawn location to X:" + player.getLocation().getX() + ", Y:" + player.getLocation().getY() + ", Z:" + player.getLocation().getZ());

        return true;
    }
}
