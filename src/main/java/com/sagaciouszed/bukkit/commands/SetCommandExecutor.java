package com.sagaciouszed.bukkit.commands;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagaciouszed.bukkit.HomeLocationFactory;
import com.sagaciouszed.bukkit.SampleHome;

/**
 * Implements the SetHome command. This command sets the player home in the
 * configuration.
 * Will overwrite the home already written to the configuration.
 */
public class SetCommandExecutor implements CommandExecutor {

    private final SampleHome plugin;

    /*
     * This CommandExecutor needs to know the plugin it came from
     */
    public SetCommandExecutor(SampleHome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            plugin.getDatabase().save(HomeLocationFactory.getHomeBean(p));
            this.plugin.getLogger().fine(MessageFormat.format("{0} set a home", p.getName()));
            p.sendMessage("You have set your home");
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
