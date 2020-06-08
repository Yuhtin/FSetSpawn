package com.yuhtin.fsetspawn.database.types;

import com.yuhtin.fsetspawn.dao.controller.SpawnerController;
import com.yuhtin.fsetspawn.database.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MySQL implements Data {
    String host, user, password, database;
    int port;

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public List<SpawnerController> loadLocations() {
        return null;
    }

    @Override
    public void save(SpawnerController controller) {

    }

    @Override
    public void close() {

    }
}
