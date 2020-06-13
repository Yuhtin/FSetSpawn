package com.yuhtin.fsetspawn.listeners;

import com.yuhtin.fsetspawn.core.controller.SpawnerController;
import com.yuhtin.fsetspawn.core.FactionSpawner;
import com.yuhtin.fsetspawn.enums.SpawnerType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class SpawnerInventoryClick implements Listener {

    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        if (!event.getInventory().getName().equalsIgnoreCase("Setar spawn dos mobs")) return;
        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        Player player = (Player) event.getWhoClicked();
        FactionSpawner controller = SpawnerController.getByUser(player.getName());
        String spawnerName = ChatColor.stripColor(item.getItemMeta().getDisplayName());
        EntityType type = SpawnerType.getByName(spawnerName);

        controller.setLocation(type, player.getLocation());
        player.sendMessage("§aVocê setou com sucesso o spawn do spawner " + ChatColor.stripColor(item.getItemMeta().getDisplayName()));
        player.closeInventory();

        SpawnerController.editing.remove(player.getName());
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent event) {
        if (event.getInventory().getName().equalsIgnoreCase("Setar spawn dos mobs"))
            SpawnerController.editing.remove(event.getPlayer().getName());
    }

}
