package com.sagaciouszed.bukkit;

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
public class SimplyHome extends JavaPlugin {
    
    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(ConfigurationSerializableLocation.class);
        
        new WorldEventListener(this);
        
        new HomeCommandExecutor(this);
        new SetCommandExecutor(this);
        new ClearCommandExecutor(this);
    }
    
    @Override
    public void onDisable() {
        ConfigurationSerialization.unregisterClass(ConfigurationSerializableLocation.class);
    }

}
