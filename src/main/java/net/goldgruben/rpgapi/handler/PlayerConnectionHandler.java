package net.goldgruben.rpgapi.handler;

import net.goldgruben.rpgapi.Rpgapi;
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
        Rpgapi.getInstance().getStatsManager().loadPlayerStats(player.getUniqueId());

        int health = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getHealth();
        int food = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getFood();

        player.setHealthScale(health);
        player.setFoodLevel(food);
        player.sendMessage(Rpgapi.getInstance().getConfig().getString("Debug") + "§4 Daten wurden geladen!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void handlePlayerQuit(final PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int health = (int) player.getHealthScale();
        int food = player.getFoodLevel();

        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setFood(food);
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setHealth(health);

        Rpgapi.getInstance().getStatsManager().unloadPlayerStats(player.getUniqueId());
    }
}
