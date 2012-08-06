package com.sagaciouszed.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;

/*
 * This is a sample event listener
 */
public class WorldEventListener implements Listener {
    private final SimplyHome plugin;

    /*
     * This listener needs to know about the plugin which it came from
     */
    public WorldEventListener(SimplyHome plugin) {
        this.plugin = plugin;
        
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onWorldSave(WorldSaveEvent event) {
        plugin.saveConfig();
    }
}
