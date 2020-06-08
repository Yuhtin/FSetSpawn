package com.yuhtin.fsetspawn.dao.controller;

import com.yuhtin.fsetspawn.utils.locations.SimpleLocation;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class SpawnerController {

    public String tag;
    public Map<EntityType, SimpleLocation> spawnLocations = new HashMap<>();

    public SpawnerController(String tag) { this.tag = tag; }

    public void setLocation(EntityType entity, Location location) {
        spawnLocations.put(entity, new SimpleLocation(location));
    }
}
