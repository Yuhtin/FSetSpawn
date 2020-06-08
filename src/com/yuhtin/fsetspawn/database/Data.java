package com.yuhtin.fsetspawn.database;

import com.yuhtin.fsetspawn.dao.controller.SpawnerController;

import java.sql.Connection;
import java.util.List;

public interface Data {

    Connection connection = null;

    boolean createTable();
    List<SpawnerController> loadLocations();
    void save(SpawnerController controller);
    void close();
}
