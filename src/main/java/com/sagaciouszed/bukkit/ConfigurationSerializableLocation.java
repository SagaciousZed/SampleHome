/*
 * Copyright (C) 2012
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal 
 * in the Software without restriction, including without limitation the rights 
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is 
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 * SOFTWARE.
 */
package com.sagaciouszed.bukkit;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * This class is a ConfigurationSerializable version of Location. It does not
 * serialize World which must be resolved the location is needed.
 */
public final class ConfigurationSerializableLocation implements ConfigurationSerializable {

    /**
     * Name of the world
     */
    private final String world;
    /**
     * UID of the world
     */
    private final String uuid;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    private transient WeakReference<Location> weakLoc;

    /**
     * Constructs a ConfigurationSerializableLocation with the information of
     * the given Location.
     * 
     * @param l
     *            Location to be serialized
     */
    public ConfigurationSerializableLocation(Location l) {
        this.world = l.getWorld().getName();
        this.uuid = l.getWorld().getUID().toString();
        this.x = l.getX();
        this.y = l.getY();
        this.z = l.getZ();
        this.yaw = l.getYaw();
        this.pitch = l.getPitch();
    }

    /**
     * Constructs a ConfigurationSerializableLocation with the given
     * Information. This constructor
     * is meant to be used if ConfigurationSerializableLocation is serialized in
     * another
     * ConfigurationSerializable class.
     * 
     * @param map
     */
    public ConfigurationSerializableLocation(Map<String, Object> map) {
        this.world = (String) map.get("world");
        this.uuid = (String) map.get("uuid");
        this.x = (Double) map.get("x");
        this.y = (Double) map.get("y");
        this.z = (Double) map.get("z");
        this.yaw = ((Double) map.get("yaw")).floatValue();
        this.pitch = ((Double) map.get("pitch")).floatValue();
    }

    /**
     * Restores from a map back into the class. Used with bukkit's
     * ConfigurationSerializable.
     * 
     * @param map
     *            a Map which represents a ConfigurationSerializableLocation
     * @return A ConfigurationSerializableLocation
     */
    public static ConfigurationSerializableLocation deserialize(Map<String, Object> map) {
        return new ConfigurationSerializableLocation(map);
    }

    /**
     * Serialize this ConfigurationSerializableLocation into a Map which contain
     * the values of
     * this class
     * 
     * @return Map<String, Object>
     */
    public final Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("world", this.world);
        map.put("uuid", this.uuid);
        map.put("x", this.x);
        map.put("y", this.y);
        map.put("z", this.z);
        map.put("yaw", this.yaw);
        map.put("pitch", this.pitch);
        return map;
    }

    /**
     * Resolves the World on the Server, as a proper location has a reference to
     * the world it belongs to.
     * 
     * @param server
     *            The server which the map is on
     * @return Location represented
     */
    public final Location getLocation() {
        if (weakLoc == null || weakLoc.get() == null) {
            World world = Bukkit.getWorld(this.uuid);
            if (world == null) {
                Logger.getLogger(this.getClass().getName()).warning("World UUID not found, falling back to World Name");
                world = Bukkit.getWorld(this.world);
            }
            if (world == null) {
                throw new IllegalStateException("Cannot find world by UUID or name");
            }
            weakLoc = new WeakReference<Location>(new Location(world, x, y, z, yaw, pitch));
        }
        return weakLoc.get();
    }
}