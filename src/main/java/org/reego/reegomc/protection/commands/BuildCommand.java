package org.reego.reegomc.protection.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.enums.MESSAGES;

import java.util.HashMap;

public class BuildCommand implements CommandExecutor {

    public static HashMap<Player, Boolean> buildMode = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou must be a player to execute this command!");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("reego.admin")) {
            player.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }

        if (!buildMode.containsKey(player)) {
            buildMode.put(player, true);
            player.sendMessage("§aBUILD MODE has been enabled!");
        } else {
            buildMode.remove(player);
            player.sendMessage("§cBUILD MODE has been disabled!");
        }

        return true;
    }

    public static boolean getBuildMode(Player player) {
        return buildMode.containsKey(player);
    }
}
