package net.goldgruben.rpgapi.mysql.users;

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

    private String volk,
                   geschlecht,
                   job;

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
        this.volk = null;
        this.geschlecht = null;
        this.job = null;
    }

    public PlayerStats(UUID uuid, int level, int xp, int bronze, int silber, int gold, int kristalle, int health, int food, int mana, String volk, String geschlecht, String job) {
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
        this.volk = volk;
        this.geschlecht = geschlecht;
        this.job = job;
    }

    public UUID getUuid() {
        return uuid;
    }
    public int getKristalle() {
        return kristalle;
    }
    public int getLevel() {
        return level;
    }
    public void setKristalle(int kristalle) {
        this.kristalle = kristalle;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXp() {
        return xp;
    }
    public int getBronze() {
        return bronze;
    }
    public int getFood() {
        return food;
    }
    public int getGold() {
        return gold;
    }
    public int getHealth() {
        return health;
    }
    public int getSilber() {
        return silber;
    }
    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public void setHealth(int health) {
        this.health = health;
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
    public String getVolk() {
        return volk;
    }
    public String getGeschlecht() {
        return geschlecht;
    }
    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }
    public void setVolk(String volk) {
        this.volk = volk;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
}
