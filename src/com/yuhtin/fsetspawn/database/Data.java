package com.yuhtin.fsetspawn.database;

import com.yuhtin.fsetspawn.dao.controller.SpawnerController;

public interface Data {

    String table = "yhfsetspawn";

    boolean openConnection();
    boolean createTable();
    boolean loadLocations();
    void save(String tag, SpawnerController controller);
    void close();
}
