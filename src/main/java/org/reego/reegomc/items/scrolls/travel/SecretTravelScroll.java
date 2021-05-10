package org.reego.reegomc.items.scrolls.travel;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.reego.reegomc.libs.ItemBuilder;

public class SecretTravelScroll {

    public static ItemStack getItem(int amount){
        return new ItemBuilder(Material.PAPER, amount)
                .setName("&aSecret Travel Scroll")
                .addGlow()
                .setLore(1, "&d&lSPECIAL")
                .addNBT("type", "scroll")
                .addNBT("scroll", "secret")
                .build();
    }

}
