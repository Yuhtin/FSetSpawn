package com.yuhtin.fsetspawn.utils.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SimpleLocation {

    public String world;
    public double x, y, z;
    public float yaw, pitch;

    public SimpleLocation(Location location) {
        this.world = location.getWorld().getName();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public World getWorld() {
        return Bukkit.getWorld(this.world);
    }

    public Location toBukkit() {
        Location location = new Location(this.getWorld(), x, y, z);

        location.setYaw(this.yaw);
        location.setPitch(this.pitch);

        return location;
    }

}
