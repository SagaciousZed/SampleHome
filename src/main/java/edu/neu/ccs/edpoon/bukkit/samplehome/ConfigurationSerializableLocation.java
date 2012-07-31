package edu.neu.ccs.edpoon.bukkit.samplehome;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Server;
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
    private transient Location loc;

    /**
     * Constructs a ConfigurationSerializableLocation with the information of the given Location.
     * @param l Location to be serialized
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
     * Constructs a ConfigurationSerializableLocation with the given Information. This constructor
     * is meant to be used if ConfigurationSerializableLocation is serialized in another
     * ConfigurationSerializable class.
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
     * @param map a Map which represents a ConfigurationSerializableLocation
     * @return A ConfigurationSerializableLocation
     */
    public static ConfigurationSerializableLocation deserialize(Map<String, Object> map) {
        return new ConfigurationSerializableLocation(map);
    }

    /**
     * Serialize this ConfigurationSerializableLocation into a Map which contain the values of
     * this class
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
     * @param server The server which the map is on
     * @return Location represented
     */
    public final Location getLocation(Server server) {
        if (loc == null) {
            World world = server.getWorld(this.uuid);
            if (world == null){
                Logger.getLogger(this.getClass().getName()).warning("World UUID not found, falling back to World Name");
                world = server.getWorld(this.world);
            }
            loc = new Location(world, x, y, z, yaw, pitch);
        }
        return loc;
    }
}