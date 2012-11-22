package com.sagaciouszed.bukkit.commands;

import gnu.trove.map.TObjectLongMap;
import gnu.trove.map.hash.TObjectLongHashMap;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagaciouszed.bukkit.Home;
import com.sagaciouszed.bukkit.HomeLocationFactory;
import com.sagaciouszed.bukkit.SampleHome;

/**
 * This class implements the Home Command. The home command send the sender home
 * if the sender has one. If not it will notify the sender to set a home.
 */
public class HomeCommandExecutor implements CommandExecutor {

    private final SampleHome plugin;
    private final TObjectLongMap<String> lastTeleport = new TObjectLongHashMap<String>();

    /*
     * This command needs to know which plugin it came from
     */
    public HomeCommandExecutor(SampleHome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            
            // check if they have teleported recently
            if (plugin.getConfig().getBoolean("limit-home")) {
                if (lastTeleport.containsKey(p.getName()) &&
                        lastTeleport.get(p.getName()) + plugin.getConfig().getLong("limit-interval-millis") > System.currentTimeMillis()) {
                    p.sendMessage("You cannot teleport home again so soon.");
                    return true;
                }
            }
            
            Home home = plugin.getDatabase().find(Home.class, p.getName());
            if (home != null) {
                p.teleport(HomeLocationFactory.getLocation(home));
                p.sendMessage("You have been sent home");
                this.plugin.getLogger().fine(MessageFormat.format("{0} teleported home", p.getName()));
                // update when the player was last teleported
                this.lastTeleport.put(p.getName(), System.currentTimeMillis());
            } else {
                return false;
            }
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
