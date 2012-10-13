package com.sagaciouszed.bukkit.commands;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagaciouszed.bukkit.ConfigurationSerializableLocation;
import com.sagaciouszed.bukkit.SimplyHome;


/**
 * Implements the SetHome command. This command sets the player home in the configuration.
 * Will overwrite the home already written to the configuration.
 */
public class SetCommandExecutor implements CommandExecutor {

    private final SimplyHome plugin;

    /*
     * This CommandExecutor needs to know the plugin it came from
     */
    public SetCommandExecutor(SimplyHome plugin) {
        this.plugin = plugin;
        plugin.getCommand("sethome");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            this.plugin.getHomesAccessor().getConfig().set(p.getName(), new ConfigurationSerializableLocation(p.getLocation()));
            this.plugin.getLogger().fine(MessageFormat.format("{0} set a home", p.getName()));
            p.sendMessage("You have set your home");
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
