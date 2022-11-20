package net.goldgruben.rpgapi;

import de.rytrox.spicy.sql.MySQL;
import net.goldgruben.rpgapi.handler.PlayerConnectionHandler;
import net.goldgruben.rpgapi.mysql.client.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RpgAPI extends JavaPlugin {

    private StatsManager statsManager;
    private MySQL database;

    private String host;
    private int port;
    private String base;
    private String username;
    private String password;

    @Override
    public void onEnable() {
        this.loadConfig();
        this.loadConfigValues();
        this.database = new MySQL(host, port, base, username, password);
        this.statsManager = new StatsManager(this);
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerConnectionHandler(this), this);
    }

    private void loadConfigValues() {
        this.host = this.getConfig().getString("mysql.host");
        this.port = this.getConfig().getInt("mysql.port");
        this.base = this.getConfig().getString("mysql.database");
        this.username = this.getConfig().getString("mysql.user");
        this.password = this.getConfig().getString("mysql.password");
    }

    public void loadConfig() {
        this.saveDefaultConfig();
        this.reloadConfig();
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }

    public MySQL getDatabase() {
        return database;
    }
}
