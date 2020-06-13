package com.yuhtin.fsetspawn.utils.serialization;

import com.google.gson.Gson;
import com.yuhtin.fsetspawn.core.FactionSpawner;

public class Serializer {
    public static Gson GSON = new Gson();

    public static String serialize(FactionSpawner controller) {
        return GSON.toJson(controller);
    }

    public static FactionSpawner deserialize(String data) {
        return GSON.fromJson(data, FactionSpawner.class);
    }
}
