package com.sagaciouszed.bukkit.commands;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagaciouszed.bukkit.ConfigurationSerializableLocation;
import com.sagaciouszed.bukkit.SimplyHome;


/**
 * This class implements the Home Command. The home command send the sender home
 * if the sender has one. If not it will notify the sender to set a home.
 */
public class HomeCommandExecutor implements CommandExecutor {

    private final SimplyHome plugin;

    public HomeCommandExecutor(SimplyHome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            final ConfigurationSerializableLocation home = (ConfigurationSerializableLocation) this.plugin.getConfig().get(p.getName());
            if (home != null) {
                p.teleport(home.getLocation(this.plugin.getServer()));
                
                p.sendMessage("You have been sent home");
                plugin.getLogger().fine(MessageFormat.format("{0} teleported home", p.getName()));
            } else {
                return false;
            }
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
