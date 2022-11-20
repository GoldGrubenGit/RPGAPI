package net.goldgruben.rpgapi.mysql.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerStats {


    private final UUID uuid;

    private int level,
            xp,
            kristalle,
            bronze,
            silber,
            gold,
            mana,
            health,
            food;

    public PlayerStats(ResultSet row) throws SQLException {
        this.uuid = UUID.fromString(row.getString("UUID"));
        this.bronze = row.getInt("BRONZE");
        this.kristalle = row.getInt("KRISTALLE");
        this.level = row.getInt("LEVEL");
        this.xp = row.getInt("XP");
        this.silber = row.getInt("SILBER");
        this.gold = row.getInt("GOLD");
        this.mana = row.getInt("MANA");
        this.health = row.getInt("HEALTH");
        this.food = row.getInt("FOOD");
    }

    public PlayerStats(UUID uuid) {
        this.uuid = uuid;
        this.bronze = 100;
        this.kristalle = 10;
        this.level = 1;
        this.xp = 0;
        this.silber = 0;
        this.gold = 0;
        this.mana = 100;
        this.health = 20;
        this.food = 20;
    }

    public PlayerStats(UUID uuid, int level, int xp, int bronze, int silber, int gold, int kristalle, int health, int food, int mana) {
        this.uuid = uuid;
        this.level = level;
        this.xp = xp;
        this.bronze = bronze;
        this.silber = silber;
        this.gold = gold;
        this.kristalle = kristalle;
        this.health = health;
        this.food = food;
        this.mana = mana;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getKristalle() {
        return kristalle;
    }

    public void setKristalle(int kristalle) {
        this.kristalle = kristalle;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSilber() {
        return silber;
    }

    public void setSilber(int silber) {
        this.silber = silber;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
