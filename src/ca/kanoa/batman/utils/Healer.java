package ca.kanoa.batman.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Healer implements Util {

	@Override
	public float getVersion() {
		return 1.0f;
	}

	@Override
	public String getName() {
		return "Healer";
	}

	@Override
	public String getDescription() {
		return "Heals people";
	}

	@Override
	public UtilType getType() {
		return UtilType.INVENTORY;
	}

	public static void heal(Player player) {
		player.setHealth(20);
		player.setFoodLevel(20);
		player.playSound(player.getLocation(), Sound.BURP, 2, 1);
	}
	
	public static void heal(String player) {
		heal(Bukkit.getPlayer(player));
	}

	public static void simulateRespawn(Player player) {
		heal(player);
		player.setExp(0);
		player.setLevel(0);
	}
	
	public static void simulateRespawn(String player) {
		simulateRespawn(Bukkit.getPlayer(player));
	}
	
}
