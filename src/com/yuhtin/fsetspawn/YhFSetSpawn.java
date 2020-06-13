package com.yuhtin.fsetspawn;

import com.yuhtin.fsetspawn.core.controller.SpawnerController;
import com.yuhtin.fsetspawn.database.Data;
import com.yuhtin.fsetspawn.database.Database;
import com.yuhtin.fsetspawn.database.types.MySQL;
import com.yuhtin.fsetspawn.database.types.SQLite;
import com.yuhtin.fsetspawn.enums.SpawnerType;
import com.yuhtin.fsetspawn.inventories.FSetSpawnInventory;
import com.yuhtin.fsetspawn.listeners.SpawnerInventoryClick;
import com.yuhtin.fsetspawn.listeners.SpawnerSpawn;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class YhFSetSpawn extends JavaPlugin {

    public static final HashMap<Integer, SpawnerType> spawners = new HashMap<>();
    public static int menuSize;
    public static Database database;

    @Override
    public void onEnable() {
        System.out.println(" ______   ______     ______     ______   ______     ______   ______     __     __     __   __    \n" +
                "/\\  ___\\ /\\  ___\\   /\\  ___\\   /\\__  _\\ /\\  ___\\   /\\  == \\ /\\  __ \\   /\\ \\  _ \\ \\   /\\ \"-.\\ \\   \n" +
                "\\ \\  __\\ \\ \\___  \\  \\ \\  __\\   \\/_/\\ \\/ \\ \\___  \\  \\ \\  _-/ \\ \\  __ \\  \\ \\ \\/ \".\\ \\  \\ \\ \\-.  \\  \n" +
                " \\ \\_\\    \\/\\_____\\  \\ \\_____\\    \\ \\_\\  \\/\\_____\\  \\ \\_\\    \\ \\_\\ \\_\\  \\ \\__/\".~\\_\\  \\ \\_\\\\\"\\_\\ \n" +
                "  \\/_/     \\/_____/   \\/_____/     \\/_/   \\/_____/   \\/_/     \\/_/\\/_/   \\/_/   \\/_/   \\/_/ \\/_/ ");
        System.out.println("Plugin yhFSetSpawn sendo iniciado");
        System.out.println("Plugin feito por Yuhtin#9147");
        if (!new File("config.yml").exists())
            saveDefaultConfig();
        loadEntities();
        registerEvents();
        if (!loadData()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        menuSize = YhFSetSpawn.getInstance().getConfig().getInt("Menu");

        System.out.println("[yhFSetSpawn] Plugin iniciado com sucesso");
    }

    @Override
    public void onDisable() {
        database.close();
    }

    private boolean loadData() {
        Data data;
        if (getConfig().getString("Database.Type").equalsIgnoreCase("MySQL"))
            data = new MySQL(getConfig().getString("Database.Host"),
                    getConfig().getString("Database.User"),
                    getConfig().getString("Database.Password"),
                    getConfig().getString("Database.Database"),
                    getConfig().getInt("Database.Port"));
        else data = new SQLite();

        database = new Database(data, "yhFSetSpawn");
        if (!database.createTable()) {
            System.out.println("Não foi possível criar a tabela do plugin");
            System.out.println("Desligando o plugin");
            return false;
        }

        database.loadLocations();
        System.out.println("Todas as localizações foram carregadas com sucesso");

        return true;
    }

    private void loadEntities() {
        getConfig().getConfigurationSection("Mobs").getKeys(false).forEach(path -> {
            if (!getConfig().getBoolean("Mobs." + path + ".Ativar")) return;
            spawners.put(getConfig().getInt("Mobs." + path + ".Slot"), SpawnerType.valueOf(path));
        });
        FSetSpawnInventory.build();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new SpawnerInventoryClick(), this);
        new SpawnerSpawn(this);
    }

    public static YhFSetSpawn getInstance() {
        return JavaPlugin.getPlugin(YhFSetSpawn.class);
    }
}
