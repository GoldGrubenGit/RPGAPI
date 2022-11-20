package net.goldgruben.rpgapi.mysql.client;

import net.goldgruben.rpgapi.RpgAPI;
import net.goldgruben.rpgapi.mysql.entity.PlayerStats;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

public class StatsManager {

    private final RpgAPI main;
    private final HashMap<UUID, PlayerStats> cachedPlayerStatsMap = new HashMap<>();

    public StatsManager(RpgAPI main) {
        this.main = main;
        this.main.getDatabase().prepare("""
                CREATE TABLE IF NOT EXISTS PLAYERDATA (
                    UUID VARCHAR(36) NOT NULL UNIQUE,
                    LEVEL INT NOT NULL DEFAULT 1,
                    XP INT NOT NULL DEFAULT 0,
                    KRISTALLE INT NOT NULL DEFAULT 10,
                    BRONZE INT NOT NULL DEFAULT 100,
                    SILBER INT NOT NULL DEFAULT 0,
                    GOLD INT NOT NULL DEFAULT 0,
                    HEALTH INT NOT NULL DEFAULT 20,
                    FOOD INT NOT NULL DEFAULT 20,
                    MANA INT NOT NULL DEFAULT 100,
                    PRIMARY KEY (UUID)
                )""")
                .execute()
                .whenComplete((booleans, throwable) ->
                        main.getLogger().log(Level.INFO, "PlayerStats Database updated!"));
    }

    public void loadPlayerStats(UUID uuid) {
        this.getPlayerStats(uuid).whenComplete((playerStats, throwable) -> {
            if (playerStats == null) {
                cachedPlayerStatsMap.put(uuid, createPlayerStats(uuid));
                return;
            }
            cachedPlayerStatsMap.put(uuid, playerStats);
        });
    }

    public void unloadPlayerStats(UUID uuid, boolean removeFromCache) {
        if (!this.cachedPlayerStatsMap.containsKey(uuid)) return;
        this.savePlayerStats(cachedPlayerStatsMap.get(uuid), removeFromCache);
    }

    public void savePlayerStats(PlayerStats playerStats, boolean removeFromCache) {
        this.updatePlayerStats(playerStats.getUuid(), playerStats);
        if (removeFromCache) cachedPlayerStatsMap.remove(playerStats.getUuid());
    }

    public CompletableFuture<PlayerStats> getPlayerStats(UUID uuid) {

        if (cachedPlayerStatsMap.containsKey(uuid))
            return CompletableFuture.completedFuture(cachedPlayerStatsMap.get(uuid));

        CompletableFuture<PlayerStats> future = new CompletableFuture<>();

        main.getDatabase().prepare("SELECT * FROM PLAYERSTATS WHERE UUID = ?", uuid.toString())
                .query(PlayerStats.class)
                .subscribe(playerStats -> {
                    if (playerStats.isEmpty()) future.complete(null);
                    cachedPlayerStatsMap.put(uuid, playerStats.get(0));
                    future.complete(playerStats.get(0));
                });

        return future;
    }

    private PlayerStats createPlayerStats(UUID uuid) {
        PlayerStats playerStats = new PlayerStats(uuid);
        this.main.getDatabase().prepare("INSERT INTO PLAYERDATA ( UUID ) VALUES ( ? )", uuid.toString())
                .execute();
        return playerStats;
    }

    private void updatePlayerStats(UUID uuid, PlayerStats playerStats) {
        this.main.getDatabase().prepare(
                "UPDATE PLAYERSTATS SET LEVEL = ?, XP = ?, BRONZE = ?, SILBER = ?, GOLD = ?, KRISTALLE = ?, HEALTH = ?, FOOD = ?, MANA = ? WHERE UUID = ?",
                playerStats.getLevel(),
                playerStats.getXp(),
                playerStats.getBronze(),
                playerStats.getSilber(),
                playerStats.getGold(),
                playerStats.getKristalle(),
                playerStats.getHealth(),
                playerStats.getFood(),
                playerStats.getMana(),
                uuid.toString()
        ).execute();
    }
}
