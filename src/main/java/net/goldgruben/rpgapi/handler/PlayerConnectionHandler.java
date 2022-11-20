package net.goldgruben.rpgapi.handler;

import net.goldgruben.rpgapi.RpgAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionHandler implements Listener {

    private final RpgAPI main;

    public PlayerConnectionHandler(RpgAPI main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        main.getStatsManager().loadPlayerStats(player.getUniqueId());

        main.getStatsManager().getPlayerStats(player.getUniqueId())
                .whenComplete((playerStats, throwable) -> {
                    int health = playerStats.getHealth();
                    int food = playerStats.getFood();

                    player.setHealthScale(health);
                    player.setFoodLevel(food);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            main.getConfig().getString("debug") + "§4 Daten wurden geladen!"));
                });
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int health = (int) player.getHealthScale();
        int food = player.getFoodLevel();

        main.getStatsManager().getPlayerStats(player.getUniqueId())
                .whenComplete((playerStats, throwable) -> {

                    playerStats.setFood(food);
                    playerStats.setHealth(health);

                    main.getStatsManager().unloadPlayerStats(player.getUniqueId(), true);
                });
    }
}
