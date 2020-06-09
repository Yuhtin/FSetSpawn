package com.yuhtin.fsetspawn.utils.serialization;

import com.google.gson.Gson;
import com.yuhtin.fsetspawn.dao.controller.SpawnerController;

public class Serializer {
    public static Gson GSON = new Gson();

    public static String serialize(SpawnerController controller) {
        return GSON.toJson(controller);
    }

    public static SpawnerController deserialize(String data) {
        return GSON.fromJson(data, SpawnerController.class);
    }
}
