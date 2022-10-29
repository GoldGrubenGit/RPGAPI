package net.goldgruben.rpgapi;

import net.goldgruben.rpgapi.handler.PlayerConnectionHandler;
import net.goldgruben.rpgapi.mysql.MySQLConnector;
import net.goldgruben.rpgapi.mysql.users.PlayerStats;
import net.goldgruben.rpgapi.mysql.users.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rpgapi extends JavaPlugin {

    private static Rpgapi instance;
    private StatsManager statsManager;

    private MySQLConnector mySQLConnector;

    private API api;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        MySQLConnector mySQLConnector = new MySQLConnector();
        mySQLConnector.connect();
        StatsManager statsManager = new StatsManager();
        api = new API();
        // Plugin startup logic
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerConnectionHandler(), this);

    }

    public void loadConfig() {
        this.saveDefaultConfig();
        this.reloadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Rpgapi getInstance() {
        return instance;
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }
    public API getApi() {
        return api;
    }
}
