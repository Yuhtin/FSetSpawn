package com.yuhtin.fsetspawn.dao;

import com.yuhtin.fsetspawn.dao.controller.SpawnerController;

import java.util.LinkedHashMap;
import java.util.Map;

public class FactionSpawnerDAO {

    public static final Map<String, SpawnerController> factions = new LinkedHashMap<>();
    public static final Map<String, String> editing = new LinkedHashMap<>();

    public static void addFaction(SpawnerController controller) {
        factions.put(controller.tag, controller);
    }

    public static SpawnerController getByUser(String name) {
        return factions.get(editing.get(name));
    }

    public static SpawnerController getByTag(String tag) { return factions.get(tag); }

    public static void create(String tag) {
        FactionSpawnerDAO.factions.put(tag, new SpawnerController(tag));
    }
}
