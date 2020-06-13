package com.yuhtin.fsetspawn.listeners;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;
import com.yuhtin.fsetspawn.core.controller.SpawnerController;
import com.yuhtin.fsetspawn.core.FactionSpawner;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnerSpawn implements Listener {

    private final JavaPlugin plugin;

    public SpawnerSpawn(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        Faction faction = BoardColl.get().getFactionAt(PS.valueOf(event.getLocation()));
        if (faction == null || faction.isNone()) return;

        FactionSpawner controller = SpawnerController.getByTag(faction.getTag());
        if (controller == null || !controller.spawnLocations.containsKey(event.getEntityType())) return;

        Bukkit.getScheduler().runTask(plugin, () -> event.getEntity().teleport(controller.spawnLocations.get(event.getEntityType()).toBukkit()));
    }
}
