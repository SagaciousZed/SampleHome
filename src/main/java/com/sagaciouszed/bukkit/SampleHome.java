package com.sagaciouszed.bukkit;

import gnu.trove.list.TLongList;
import gnu.trove.list.array.TLongArrayList;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import com.sagaciouszed.bukkit.commands.ClearCommandExecutor;
import com.sagaciouszed.bukkit.commands.HomeCommandExecutor;
import com.sagaciouszed.bukkit.commands.SetCommandExecutor;


/**
 * This is the main class of the plugin SimplyHome. This plugin has a very
 * simple home system. It was made to be more of an example of Bukkit's
 * Configuration API. SimplyHome is unique in that respect as it stores Location
 * in a ConfigurationSerilizable class.
 */
public class SampleHome extends JavaPlugin {
    
    private ConfigAccessor homesAccessor;
    
    @Override
    public void onEnable() {
        // Write out the default config to disk if it does not exist
        this.saveDefaultConfig();
        
        // Register class
        ConfigurationSerialization.registerClass(ConfigurationSerializableLocation.class);
        
        // Initialize homes.yml access
        this.homesAccessor = new ConfigAccessor(this, "homes.yml");

        // Create Listener
        new WorldEventListener(this);
        
        // Create CommandExecutors
        this.getCommand("home").setExecutor(new HomeCommandExecutor(this));
        this.getCommand("clearhome").setExecutor(new ClearCommandExecutor(this));
        this.getCommand("sethome").setExecutor(new SetCommandExecutor(this));        
    }
    
    @Override
    public void onDisable() {
        // Unregister class
        ConfigurationSerialization.unregisterClass(ConfigurationSerializableLocation.class);
    }
    
    public ConfigAccessor getHomesAccessor() {
        return this.homesAccessor;
    }

}
