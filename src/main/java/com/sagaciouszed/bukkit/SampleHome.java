package com.sagaciouszed.bukkit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import gnu.trove.list.TLongList;
import gnu.trove.list.array.TLongArrayList;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import com.avaje.ebean.CallableSql;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.LogLevel;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.avaje.ebean.SqlUpdate;
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

    @Override
    public void onEnable() {
        // Write out the default config to disk if it does not exist
        this.saveDefaultConfig();

        /*
         * Run a simple query to see if the table exsits, if it doen't create
         * the table.
         */
        try {
            getDatabase().find(Home.class).findList();
        } catch (RuntimeException e) {
            getLogger().log(Level.INFO, "Installing DDL");
            installDDL();
        }

        // Create CommandExecutors
        this.getCommand("home").setExecutor(new HomeCommandExecutor(this));
        this.getCommand("clearhome").setExecutor(new ClearCommandExecutor(this));
        this.getCommand("sethome").setExecutor(new SetCommandExecutor(this));
    }

    /**
     * Generate a list of classes that the Database will need to know about
     * 
     * @see org.bukkit.plugin.java.JavaPlugin#getDatabaseClasses()
     */
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(Home.class);
        return classes;
    }

}
