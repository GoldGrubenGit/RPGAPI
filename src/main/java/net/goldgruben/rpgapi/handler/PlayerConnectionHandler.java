package net.goldgruben.rpgapi.handler;

import net.goldgruben.rpgapi.RpgApi;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionHandler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        RpgApi.getInstance().getStatsManager().loadPlayerStats(player.getUniqueId());

        int health = RpgApi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getHealth();
        int food = RpgApi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getFood();

        player.setHealthScale(health);
        player.setFoodLevel(food);
        player.sendMessage(RpgApi.getInstance().getConfig().getString("Debug") + "§4 Daten wurden geladen!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int health = (int) player.getHealthScale();
        int food = player.getFoodLevel();

        RpgApi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setFood(food);
        RpgApi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setHealth(health);

        RpgApi.getInstance().getStatsManager().unloadPlayerStats(player.getUniqueId());
    }
}
