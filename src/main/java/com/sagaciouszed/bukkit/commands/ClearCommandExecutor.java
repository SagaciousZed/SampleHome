package com.sagaciouszed.bukkit.commands;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagaciouszed.bukkit.SimplyHome;


public class ClearCommandExecutor implements CommandExecutor {
    private final SimplyHome plugin;

    /*
     * This command needs to know which plugin it came from
     */
    public ClearCommandExecutor(SimplyHome plugin) {
        this.plugin = plugin;
        plugin.getCommand("clearhome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            this.plugin.getHomesAccessor().getConfig().set(sender.getName(), null);
            this.plugin.getLogger().fine(MessageFormat.format("{0} cleared a home", sender.getName()));
            sender.sendMessage("You have cleared your home.");
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
