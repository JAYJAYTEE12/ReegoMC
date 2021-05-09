package org.reego.reegomc.economy.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.economy.utils.EconomyUtils;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.utils.NumberUtils;

public class CoinsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cIncorrect Usage! /coins (player) (set/add/remove) (amount)");
                return true;
            }
            sender.sendMessage("§fYour coins: §6" + EconomyUtils.getCoins((Player) sender));
            return true;
        } else if (args.length == 3) {

            if(!(sender.hasPermission("reego.admin"))){
                sender.sendMessage(MESSAGES.NO_PERMISSION.message);
                return true;
            }

            OfflinePlayer targetOffline = Bukkit.getOfflinePlayer(args[0]);
            Player target = (Player) targetOffline;

            // If player has an account
            if (EconomyUtils.getCoins(target) == null) {
                sender.sendMessage("§cThat player doesn't have an account!");
                return true;
            }

            // Check if the amount is an integer
            if (!NumberUtils.isInt(args[2])) {
                sender.sendMessage("§cThe amount isn't a number!");
                return true;
            }
            Integer amount = Integer.parseInt(args[2]);

            if (args[1].equalsIgnoreCase("set")) {
                EconomyUtils.setBalance(target, amount);
                sender.sendMessage("§aSuccessfully set " + target.getName() + "'s Coins to §6" + amount);
                if (targetOffline.isOnline()) target.sendMessage("§aYour coins has been set to §6" + amount);
            }else if(args[1].equalsIgnoreCase("add")){
                EconomyUtils.addBalance(target, amount);
                sender.sendMessage("§aSuccessfully added §6" + amount + "§a Coins to " + target.getName() + "'s Coins");
                if (targetOffline.isOnline()) target.sendMessage("§6"+amount+" §aCoins has been added to your account");
            }else if(args[1].equalsIgnoreCase("remove")){
                EconomyUtils.removeBalance(target, amount);
                sender.sendMessage("§aSuccessfully removed §6" + amount + "§a Coins from " + target.getName() + "'s Coins");
                if (targetOffline.isOnline()) target.sendMessage("§6"+amount+" §aCoins has been removed from your account");
            }else{
                sender.sendMessage("§cIncorrect usage! /coins (player) (set/add/remove) (amount)");
            }

        }else{
            if(!(sender.hasPermission("reego.admin"))){
                sender.sendMessage(MESSAGES.NO_PERMISSION.message);
                return true;
            }
            sender.sendMessage("§cIncorrect usage! /coins (player) (set/add/remove) (amount)");
        }
        return true;
    }
}
