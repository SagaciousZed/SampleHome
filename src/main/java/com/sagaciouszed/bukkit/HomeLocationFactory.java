package com.sagaciouszed.bukkit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This is a factory class to make dealing with the bean easier.
 */
public class HomeLocationFactory {

    public static Home getHomeBean(final Player p) {
        final Location loc = p.getLocation();
        final Home bean = new Home();
        bean.setName(p.getName());
        bean.setWorldUuid(loc.getWorld().getUID().toString());
        bean.setLocX(loc.getX());
        bean.setLocY(loc.getY());
        bean.setLocZ(loc.getZ());
        bean.setLocYaw(loc.getYaw());
        bean.setLocPitch(loc.getPitch());
        return bean;
    }

    public static Location getLocation(final Home home) {
        return new Location(
                Bukkit.getWorld(UUID.fromString(home.getWorldUuid())),
                home.getLocX(),
                home.getLocY(),
                home.getLocZ(),
                home.getLocYaw(),
                home.getLocPitch());
    }
}
