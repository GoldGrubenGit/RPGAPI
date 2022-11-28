package net.goldgruben.rpgapi;

import net.goldgruben.rpgapi.handler.PlayerConnectionHandler;
import net.goldgruben.rpgapi.mysql.MySQLConnector;
import net.goldgruben.rpgapi.mysql.users.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RpgApi extends JavaPlugin {

    private static RpgApi instance;
    private StatsManager statsManager;

    private MySQLConnector mySQLConnector;

    private API api;

    static String host;
    private int port = RpgApi.getInstance().getConfig().getInt("MySql.port");
    static String database;
    static String username;
    static String password;

    static {
        host = RpgApi.getInstance().getConfig().getString("MySql.host");
        database = RpgApi.getInstance().getConfig().getString("MySql.database");
        username = RpgApi.getInstance().getConfig().getString("MySql.user");
        password = RpgApi.getInstance().getConfig().getString("MySql.password");
    }

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        MySQLConnector mySQLConnector = new MySQLConnector();
        mySQLConnector.connect(host, database, port, password, username);
        StatsManager statsManager = new StatsManager();
        api = new API();
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

    public static RpgApi getInstance() {
        return instance;
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }
    public API getApi() {
        return api;
    }
}
