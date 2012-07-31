package edu.neu.ccs.edpoon.bukkit.samplehome.commands;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import edu.neu.ccs.edpoon.bukkit.samplehome.ConfigurationSerializableLocation;
import edu.neu.ccs.edpoon.bukkit.samplehome.SimplyHome;

/**
 * Implements the SetHome command. This command sets the player home in the configuration.
 * Will overwrite the home already written to the configuration.
 */
public class SetCommandExecutor implements CommandExecutor {

    private final SimplyHome plugin;

    public SetCommandExecutor(SimplyHome plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            this.plugin.getConfig().set(p.getName(), new ConfigurationSerializableLocation(p.getLocation()));
            
            p.sendMessage("You have set your home");
            plugin.getLogger().fine(MessageFormat.format("{0} set a home", p.getName()));
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
