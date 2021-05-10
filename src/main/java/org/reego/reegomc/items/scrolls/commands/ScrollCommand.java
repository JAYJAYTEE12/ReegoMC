package org.reego.reegomc.items.scrolls.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.reego.reegomc.enums.MESSAGES;
import org.reego.reegomc.items.scrolls.travel.SecretTravelScroll;
import org.reego.reegomc.utils.NumberUtils;

public class ScrollCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("reego.admin")){
            sender.sendMessage(MESSAGES.NO_PERMISSION.message);
            return true;
        }

        if(args.length != 3){
            sender.sendMessage("§cIncorrect Usage! /scroll (player) (scroll) (amount)");
            return true;
        }else if(args.length == 3){

            Player target = (Player) Bukkit.getPlayer(args[0]);
            if(target == null){
                sender.sendMessage(MESSAGES.NOT_PLAYER.message);
                return true;
            }

            if (!NumberUtils.isInt(args[2])) {
                sender.sendMessage("§cThe amount isn't a number!");
                return true;
            }
            Integer amount = Integer.parseInt(args[2]);

            if(args[1].equalsIgnoreCase("secret")){
                target.getInventory().addItem(SecretTravelScroll.getItem(amount));
                target.sendMessage("§aYou've been given a secret travel scroll §7(x"+amount+")§a!");
                sender.sendMessage("§aYou've given " + target.getName() + " secret travel scroll §7(x"+amount+")");
            }else{
                sender.sendMessage("§cIncorrect Usage! /scroll (player) (scroll) (amount)");
            }

        }else{
            sender.sendMessage("§cIncorrect Usage! /scroll (player) (scroll) (amount)");
        }
        return true;
    }
}
