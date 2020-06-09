package com.yuhtin.fsetspawn.database.types;

import com.yuhtin.fsetspawn.dao.controller.SpawnerController;
import com.yuhtin.fsetspawn.database.Data;

import java.util.List;

public class SQLite implements Data {


    @Override
    public boolean openConnection() {

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
