package org.reego.reegomc.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.server.utils.TeleportUtils;

public class GoToCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("§cYou must be a player to execute this command!");
            return true;
        }

        if(!sender.hasPermission("reego.admin")){
            sender.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }

        if(args.length != 2){
            sender.sendMessage("§cIncorrect Usage! /goto (player) (world)");
            return true;
        }

        Player target = (Player) Bukkit.getPlayer(args[0]);
        if(target == null){
            sender.sendMessage(MESSAGES.NOT_PLAYER.message);
            return true;
        }

        if(!TeleportUtils.goToWorld(args[1], target)){
            sender.sendMessage("§cThat world doesn't exist!");
            return true;
        }

        target.sendMessage("§aYou've been teleported to the " + args[1] + " world!");
        sender.sendMessage("§aYou've teleported "+ args[0] + " to the " + args[1] + " world!");

        return true;
    }
}
