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
		Main.stats.remove(Main.getStatsForPlayer(stats.getPlayerName()));
		Main.stats.add(stats);
	}
	
	public static void addKill(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.stats.remove(stats);
		stats.addKill();
		Main.stats.add(stats);
	}
	
	public static int getKills(String player) {
		return Main.getStatsForPlayer(player).getKills();
	}
	
	public static void addDeath(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.stats.remove(stats);
		stats.addDeath();
		Main.stats.add(stats);
	}
	
	public static int getDeaths(String player) {
		return Main.getStatsForPlayer(player).getDeaths();
	}
	
	public static void addWin(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.stats.remove(stats);
		stats.addWin();
		Main.stats.add(stats);
	}
	
	public static int getWins(String player) {
		return Main.getStatsForPlayer(player).getWins();
	}
	
	public static void addRodUsed(String player) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.stats.remove(stats);
		stats.addRodUsed();
		Main.stats.add(stats);
	}
	
	public static int getRodsUsed(String player) {
		return Main.getStatsForPlayer(player).getUsedRods();
	}
	
	public static void setKillstreak(String player, int killstreak) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.stats.remove(stats);
		stats.setKillstreak(killstreak);
		Main.stats.add(stats);
	}
	
	public static int getKillstreak(String player) {
		return Main.getStatsForPlayer(player).getKillstreak();
	}
	
	public static void addMoney(String player, int money) {
		Stats stats = Main.getStatsForPlayer(player);
		Main.stats.remove(stats);
		stats.addMoney(money);
		Main.stats.add(stats);
	}
	
	public static int getMoney(String player) {
		return Main.getStatsForPlayer(player).getMoney();
	}

}
