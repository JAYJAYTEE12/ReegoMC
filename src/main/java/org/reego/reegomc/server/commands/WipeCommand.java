package org.reego.reegomc.server.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.economy.utils.EconomyUtils;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.protection.commands.BuildCommand;

public class WipeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            sender.sendMessage("§cYou can't use this command as a player!");
            return true;
        }

        if(args.length != 1){
            sender.sendMessage("§cIncorrect Usage! /wipe (player)");
            return true;
        }

        Player player = (Player) Bukkit.getPlayer(args[0]);
        if(player == null){
            sender.sendMessage(MESSAGES.NOT_PLAYER.message);
            return true;
        }

        EconomyUtils.deleteBalance(player);
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        if(BuildCommand.getBuildMode(player)) BuildCommand.buildMode.remove(player);
        player.kickPlayer("§cYour account has been wiped! Naughty boy...");

        return true;
    }
}
