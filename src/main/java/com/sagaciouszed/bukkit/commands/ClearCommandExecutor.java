package com.sagaciouszed.bukkit.commands;

import java.text.MessageFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagaciouszed.bukkit.Home;
import com.sagaciouszed.bukkit.SampleHome;


public class ClearCommandExecutor implements CommandExecutor {
    private final SampleHome plugin;

    /*
     * This command needs to know which plugin it came from
     */
    public ClearCommandExecutor(SampleHome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            
            // Search to see if it is in the database then drop it.
            Home home = this.plugin.getDatabase().find(Home.class, p.getName());
            if (home != null) {
                this.plugin.getDatabase().delete(home);

                this.plugin.getLogger().fine(MessageFormat.format("{0} cleared a home", sender.getName()));
                sender.sendMessage("You have cleared your home.");
            }
        } else {
            sender.sendMessage("You must be a player to have a home");
        }
        return true;
    }
}
