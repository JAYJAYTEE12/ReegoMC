package org.reego.reegomc.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.server.utils.ServerUtils;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){

            if(!sender.hasPermission("reego.admin")){
                sender.sendMessage(MESSAGES.NO_PERMISSION.message);
                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if(player == null){
                sender.sendMessage("§cThat play isn't online!");
            }

        }

        if(!(sender instanceof Player)){
            sender.sendMessage(MESSAGES.NOT_PLAYER.message);
            return true;
        }

        if(ServerUtils.getSpawn() == null){
            sender.sendMessage("§cThe spawn area hasn't been set yet! Please contact an admin.");
            return true;
        }

        Player player = (Player) sender;
        player.teleport(ServerUtils.getSpawn());
        player.sendMessage("§aSuccessfully teleported to spawn!");

        return true;
    }

}
