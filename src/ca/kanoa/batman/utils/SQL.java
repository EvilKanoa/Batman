package ca.kanoa.batman.utils;

import org.bukkit.entity.Player;

import ca.kanoa.batman.sql.Main;
import ca.kanoa.batman.sql.Stats;

public class SQL implements Util {

	@Override
	public float getVersion() {
		return (float) 2.0;
	}

	@Override
	public String getName() {
		return "SQL";
	}

	@Override
	public String getDescription() {
		return "Allows easy access to the SQL stats";
	}

	@Override
	public UtilType getType() {
		return UtilType.DATABASE;
	}
	
	public static Stats getStatsForPlayer(String player) {
		return Main.getStatsForPlayer(player);
	}
	
	public static Stats getStatsForPlayer(Player player) {
		return Main.getStatsForPlayer(player.getName());
	}
	
	public static void setStats(Stats stats) {
		Main.removeStat(Main.getStatsForPlayer(stats.getPlayerName()));
		Main.addStat(stats);
	}
	
	public static void addKill(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.addKill();
		Main.addStat(stats);
	}
	
	public static int getKills(String player) {
		return Main.getStatsForPlayer(player).getKills();
	}
	
	public static void addDeath(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.addDeath();
		Main.addStat(stats);
	}
	
	public static int getDeaths(String player) {
		return Main.getStatsForPlayer(player).getDeaths();
	}
	
	public static void addWin(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.addWin();
		Main.addStat(stats);
	}
	
	public static int getWins(String player) {
		return Main.getStatsForPlayer(player).getWins();
	}
	
	public static void addRodUsed(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.addRodUsed();
		Main.addStat(stats);
	}
	
	public static int getRodsUsed(String player) {
		return Main.getStatsForPlayer(player).getUsedRods();
	}
	
	public static void setKillstreak(String player, int killstreak) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.setKillstreak(killstreak);
		Main.addStat(stats);
	}
	
	public static int getKillstreak(String player) {
		return Main.getStatsForPlayer(player).getKillstreak();
	}
	
	public static void addMoney(String player, int money) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.addMoney(money);
		Main.addStat(stats);
	}
	
	public static void removeMoney(String player, int money) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.removeStat(stats);
		stats.removeMoney(money);
		Main.addStat(stats);
	}
	
	public static int getMoney(String player) {
		return Main.getStatsForPlayer(player).getMoney();
	}

}
