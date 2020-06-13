package com.yuhtin.fsetspawn.core.controller;

import com.yuhtin.fsetspawn.YhFSetSpawn;
import com.yuhtin.fsetspawn.core.FactionSpawner;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpawnerController {

    public static final Map<String, FactionSpawner> factions = new LinkedHashMap<>();
    public static final Map<String, String> editing = new LinkedHashMap<>();

    public static void addFaction(FactionSpawner controller) {
        factions.put(controller.tag, controller);
    }

    public static FactionSpawner getByUser(String name) {
        return factions.get(editing.get(name));
    }

    public static FactionSpawner getByTag(String tag) {
        return factions.get(tag);
    }

    public static void create(String tag) {
        SpawnerController.factions.put(tag, new FactionSpawner(tag));
        YhFSetSpawn.database.create(tag);
    }
}
