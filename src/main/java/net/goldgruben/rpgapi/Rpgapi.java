package net.goldgruben.rpgapi;

import net.goldgruben.rpgapi.mysql.MySQLConnector;
import net.goldgruben.rpgapi.mysql.users.PlayerStats;
import net.goldgruben.rpgapi.mysql.users.StatsManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Rpgapi extends JavaPlugin {

    private static Rpgapi instance;
    private StatsManager statsManager;
    private PlayerStats playerStats;
    private MySQLConnector mySQLConnector;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        MySQLConnector mySQLConnector = new MySQLConnector();
        mySQLConnector.connect();
        StatsManager statsManager = new StatsManager();
        // Plugin startup logic

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

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }
}
