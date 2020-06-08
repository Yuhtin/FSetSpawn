package com.yuhtin.fsetspawn.inventories;

import com.yuhtin.fsetspawn.YhFSetSpawn;
import com.yuhtin.fsetspawn.dao.FactionSpawnerDAO;
import com.yuhtin.fsetspawn.utils.builders.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class FSetSpawnInventory {
    public static final Inventory inventory = Bukkit.createInventory(null, YhFSetSpawn.menuSize, "Setar spawn dos spawners");

    public static void build() {
        YhFSetSpawn.spawners.forEach((loc, type) -> {
            inventory.setItem(loc, new ItemBuilder(type.head).name("§6" + type.name).lore("§fClique para setar o spawn", "§fdos spawners de §e" + type.name).result());
        });
        System.out.println("Menu de spawners instanciado com sucesso");
    }

    public static void open(Player player, String tag) {
        player.openInventory(inventory);
        FactionSpawnerDAO.editing.put(player.getName(), tag);
    }
}
