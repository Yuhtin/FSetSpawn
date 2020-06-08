package com.yuhtin.fsetspawn.commands;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.yuhtin.fsetspawn.dao.FactionSpawnerDAO;
import com.yuhtin.fsetspawn.inventories.FSetSpawnInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class FSetSpawnCommand implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equalsIgnoreCase("/f setspawn") || event.getMessage().equalsIgnoreCase("/f setspawner")) {
            event.setCancelled(true);

            MPlayer value = MPlayer.get(player);
            if (!value.hasFaction()) {
                player.sendMessage("§cVocê precisa ter uma facção para fazer isto.");
                return;
            }

            if (value.getRole() != Rel.LEADER && value.getRole() != Rel.OFFICER) {
                player.sendMessage("§cVocê precisa ser capitão ou superior da facção para fazer isto.");
                return;
            }

            Faction faction = BoardColl.get().getFactionAt(PS.valueOf(player.getLocation()));
            if (faction == null || faction != value.getFaction()) {
                player.sendMessage("§cVocê precisa estar no claim de sua facção para fazer isto.");
                return;
            }

            if (!FactionSpawnerDAO.factions.containsKey(faction.getTag()))
                FactionSpawnerDAO.create(faction.getTag());

            FSetSpawnInventory.open(player, faction.getTag());
        }
    }

}
