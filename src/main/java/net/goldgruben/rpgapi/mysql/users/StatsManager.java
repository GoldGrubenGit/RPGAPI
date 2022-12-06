package net.goldgruben.rpgapi.mysql.users;

import net.goldgruben.rpgapi.mysql.MySQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class StatsManager {

        private final HashMap<UUID, PlayerStats> cachedPlayerStatsMap;

        public StatsManager() {
            cachedPlayerStatsMap = new HashMap<>();
            try {
                MySQLConnector.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `playerdata` (\n"+
                        "\t `uuid` VARCHAR(36) CHARACTER SET utf8 COLLATE  utf8_general_ci NOT NULL, \n" +
                        "\t `level` INT NOT NULL DEFAULT '1', \n" +
                        "\t `xp` INT NOT NULL DEFAULT '0', \n" +
                        "\t `kristalle` INT NOT NULL DEFAULT '10', \n" +
                        "\t `bronze` INT NOT NULL DEFAULT '100', \n" +
                        "\t `silber` INT NOT NULL DEFAULT '0', \n" +
                        "\t `gold` INT NOT NULL DEFAULT '0', \n" +
                        "\t `health` INT NOT NULL DEFAULT '20', \n" +
                        "\t `food` INT NOT NULL DEFAULT '20', \n" +
                        "\t `mana` INT NOT NULL DEFAULT '100', \n" +
                        "\t `volk` VARCHAR(36) DEFAULT NULL, \t" +
                        "\t `geschlecht` VARCHAR(36) DEFAULT NULL, \t" +
                        "\t `job` VARCHAR(36) DEFAULT NULL, \t" +
                        "\tPRIMARY KEY (`uuid`)\n" +
                        ");").executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        public void loadPlayerStats(UUID uuid) {
            PlayerStats playerStats = getPlayerStats(uuid);
            if(playerStats == null) {
                playerStats = createPlayerStats(uuid);
            }
            cachedPlayerStatsMap.put(uuid, playerStats);
        }

        public void unloadPlayerStats(UUID uuid) {
            savePlayerStats(getPlayerStats(uuid), true);
        }

        public void savePlayerStats(PlayerStats playerStats, boolean removeFromCache) {
            updatePlayerStats(playerStats.getUuid(), playerStats);
            if(removeFromCache) {
                cachedPlayerStatsMap.remove(playerStats.getUuid());
            }
        }

        public PlayerStats createPlayerStats(UUID uuid) {
            PlayerStats playerStats = new PlayerStats(uuid);

            try {
                MySQLConnector.getConnection().prepareStatement("INSERT INTO `playerdata` (`uuid`) VALUES ('" + playerStats.getUuid().toString() + "')").executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return playerStats;

        }

        public PlayerStats getPlayerStats(UUID uuid) {
            if(cachedPlayerStatsMap.containsKey(uuid)) {
                return cachedPlayerStatsMap.get(uuid);
            }
            PlayerStats playerStats = null;

            try {
                ResultSet resultSet = MySQLConnector.getConnection().prepareStatement("SELECT * FROM `playerdata` WHERE `uuid` = '" + uuid.toString() + "'").executeQuery();
                if(resultSet.next()) {
                    playerStats = new PlayerStats(uuid, resultSet.getInt("level"), resultSet.getInt("xp"), resultSet.getInt("bronze"), resultSet.getInt("silber"), resultSet.getInt("gold"), resultSet.getInt("kristalle"), resultSet.getInt("health"), resultSet.getInt("food"), resultSet.getInt("mana"), resultSet.getString("volk"), resultSet.getString("geschlecht"), resultSet.getString("job"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(playerStats != null) {
                cachedPlayerStatsMap.put(uuid, playerStats);
            }
            return playerStats;

        }

        public void updatePlayerStats(UUID uuid, PlayerStats playerStats) {
            try {
                MySQLConnector.getConnection().prepareStatement("UPDATE `playerdata` SET `level` = '" + playerStats.getLevel() + "', `xp` = '" + playerStats.getXp() + "', `bronze` = '" + playerStats.getBronze() + "',  `silber` = '" + playerStats.getSilber() +  "', `gold` = '" + playerStats.getGold() + "', `kristalle` = '" + playerStats.getKristalle() + "', `health` = '" + playerStats.getHealth() + "', `food` = '" + playerStats.getFood() + "', `mana` = '" + playerStats.getMana() + "', `volk` = '" + playerStats.getVolk() + "', `geschlecht` = '" + playerStats.getGeschlecht() + playerStats.getJob() + "' WHERE `uuid` = '" + playerStats.getUuid().toString() + "'");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
