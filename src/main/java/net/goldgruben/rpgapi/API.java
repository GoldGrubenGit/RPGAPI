package net.goldgruben.rpgapi;

import net.goldgruben.rpgapi.mysql.users.PlayerStats;
import org.bukkit.entity.Player;

import java.util.UUID;

public class API {

    //getter

    public int getBronze(Player player) {
        int bronze = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getBronze();
        return bronze;
    }

    public int getSilber(Player player) {
        int silber = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getSilber();
        return silber;
    }

    public int getGold(Player player) {
        int gold = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getGold();
        return gold;
    }

    public int getLevel(Player player) {
        int level = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getLevel();
        return level;
    }

    public int getXP(Player player) {
        int xp = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getXp();
        return xp;
    }

    public int getkristalle(Player player) {
        int kirstalle = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getKristalle();
        return kirstalle;
    }

    public int getMana(Player player) {
        int mana = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getMana();
        return mana;
    }

    //setter

    public void setBronze(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setBronze(amount);
    }

    public void setSilber(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setSilber(amount);
    }

    public void setGold(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setGold(amount);
    }

    public void setLevel(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setLevel(amount);
    }

    public void setXP(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setXp(amount);
    }

    public void setMana(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setMana(amount);
    }

    public void setKristalle(Player player, int amount) {
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setKristalle(amount);
    }

    //adder

    public void addBronze(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getBronze();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setBronze(i);
    }

    public void addSilber(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getSilber();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setSilber(i);
    }

    public void addGold(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getGold();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setGold(i);
    }

    public void addLevel(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getLevel();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setLevel(i);
    }

    public void addXP(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getXp();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setXp(i);
    }

    public void addKristalle(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getKristalle();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setKristalle(i);
    }

    public void addMana(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getMana();
        i = i + amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setMana(i);
    }

    //remover

    public void removeBronze(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getBronze();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setBronze(i);
    }

    public void removeSilber(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getSilber();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setSilber(i);
    }

    public void removeGold(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getGold();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setGold(i);
    }

    public void removeLevel(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getLevel();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setLevel(i);
    }

    public void removeXP(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getXp();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setXp(i);
    }

    public void removeKristalle(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getKristalle();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setKristalle(i);
    }

    public void removeMana(Player player, int amount) {
        int i = Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).getMana();
        i = i - amount;
        Rpgapi.getInstance().getStatsManager().getPlayerStats(player.getUniqueId()).setMana(i);
    }
}
